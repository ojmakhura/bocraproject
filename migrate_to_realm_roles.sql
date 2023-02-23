UPDATE public.authorisation_roles
	SET roles='ALL_USERS'
	WHERE roles='PUBLIC_USER';
		
UPDATE public.authorisation_roles
	SET roles='BOCRA_PORTAL_USER'
	WHERE roles='BOCRA_WEB_USER';
	
UPDATE public.authorisation_roles
	SET roles='BOCRA_PORTAL_ADMIN'
	WHERE roles='BOCRA_WEB_ADMIN';

UPDATE public.form_roles
	SET roles='ALL_USERS'
	WHERE roles='PUBLIC_USER';
		
UPDATE public.form_roles
	SET roles='BOCRA_PORTAL_USER'
	WHERE roles='BOCRA_WEB_USER';
	
UPDATE public.form_roles
	SET roles='BOCRA_PORTAL_ADMIN'
	WHERE roles='BOCRA_WEB_ADMIN';

