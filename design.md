## API

### Add new app
```
POST /agora/app
{
    "appId": "",
    "appCert": "",
    "expirationTimeInSec": 600
}
```
```
{
    "success": true
}
```

### Generate new token for app
```
POST /agora/token
{
    "appId": "required",
    "uid": "",
    "channelName": "",
    "role": 1
}
```
```
{
    "token": "result"
}
```

### Allow user access to app
```
POST /user/allow
{
    "email": "",
    "appId": ""
}
```
### Revoke user access to app
```
DELETE /user/allow
{
    "email": "",
    "appId": ""
}
```
### List users (with allowed apps)
```
GET /user
```