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
```JSON
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
```JSON
{
    "token": "result"
}
```

### Allow user access to app
```
POST /allowance
{
    "email": "",
    "appId": ""
}
```
### Revoke user access to app
```
DELETE /allowance
{
    "email": "",
    "appId": ""
}
```
### Check user has access to app
```
POST /allowance/check
{
    "email": "",
    "appId": ""
}
```
result:
```JSON
{
  "result": true
}
```
or
```JSON
{
  "result": false
}
```
### List users (with allowed apps)
```
GET /allowance/user
```
```JSON
[
  {
    "email": "a@a.com",
    "appIds": ["aaa1", "aaa2"]
  },
  {
    "email": "b@b.com",
    "appIds": ["bbb1"]
  }
]
```