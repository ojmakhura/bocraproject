import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { environment } from '@env/environment';
import { Logger, UntilDestroy } from '@shared';

const log = new Logger('Login');

@UntilDestroy()
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  version: string | null = environment.version;
  error: string | undefined;
  loginForm!: FormGroup;
  isLoading = false;

  constructor(private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {}

  login() {
    this.isLoading = true;
    // const login$ = this.authenticationService.login(this.loginForm.value);
    // login$.pipe(
    //   finalize(() => {
    //     this.loginForm.markAsPristine();
    //     this.isLoading = false;
    //   }),
    //   untilDestroyed(this)
    // ).subscribe(credentials => {
    //   log.debug(`${credentials.username} successfully logged in`);
    //   this.router.navigate([ this.route.snapshot.queryParams['redirect'] || '/'], { replaceUrl: true });
    // }, error => {
    //   log.debug(`Login error: ${messages}`);
    //   this.errors = error;
    // });
  }

  private createForm() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      remember: true,
    });
  }
}
