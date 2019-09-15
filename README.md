# NOTIFICATION TEMPLATES

### Test

API rest which sends an html email using spring boot and thymeleaf templates, email subject is internationalized too at file Subject_xx using template name.

Just configure mail.server.username and mail.server.password at emailconfig.properties (this example uses gmail, you should configure another properties like port if you use another email server)

Run the application an try:

```
localhost:8080/notification/email

{
	"email": <<email to>>,
	"locale": <<locale es or en, if another default en>>,
	"template": wellcome,
	"userName": <<user name>>
}

```

By now there is only one template called wellcome.
