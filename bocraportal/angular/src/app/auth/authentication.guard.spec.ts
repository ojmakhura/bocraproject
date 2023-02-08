import { TestBed } from '@angular/core/testing';
import { Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { CredentialsService } from './credentials.service';
import { MockCredentialsService } from './credentials.service.mock';
import { AuthenticationAuthorisation } from './authentication.authorisation';

describe('AuthenticationAuthorisation', () => {
  let authenticationAuthorisation: AuthenticationAuthorisation;
  let credentialsService: MockCredentialsService;
  let mockRouter: any;
  let mockSnapshot: RouterStateSnapshot;

  beforeEach(() => {
    mockRouter = {
      navigate: jasmine.createSpy('navigate'),
    };
    mockSnapshot = jasmine.createSpyObj<RouterStateSnapshot>('RouterStateSnapshot', ['toString']);

    TestBed.configureTestingModule({
      providers: [
        AuthenticationAuthorisation,
        { provide: CredentialsService, useClass: MockCredentialsService },
        { provide: Router, useValue: mockRouter },
      ],
    });

    authenticationAuthorisation = TestBed.inject(AuthenticationAuthorisation);
    credentialsService = TestBed.inject(CredentialsService);
  });

  it('should have a canActivate method', () => {
    expect(typeof authenticationAuthorisation.canActivate).toBe('function');
  });

  it('should return true if user is authenticated', () => {
    expect(authenticationAuthorisation.canActivate(new ActivatedRouteSnapshot(), mockSnapshot)).toBe(true);
  });

  it('should return false and redirect to login if user is not authenticated', () => {
    // Arrange
    credentialsService.credentials = null;

    // Act
    const result = authenticationAuthorisation.canActivate(new ActivatedRouteSnapshot(), mockSnapshot);

    // Assert
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/login'], {
      queryParams: { redirect: undefined },
      replaceUrl: true,
    });
    expect(result).toBe(false);
  });

  it('should save url as queryParam if user is not authenticated', () => {
    credentialsService.credentials = null;
    mockRouter.url = '/about';
    mockSnapshot.url = '/about';

    authenticationAuthorisation.canActivate(new ActivatedRouteSnapshot(), mockSnapshot);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/login'], {
      queryParams: { redirect: mockRouter.url },
      replaceUrl: true,
    });
  });
});
