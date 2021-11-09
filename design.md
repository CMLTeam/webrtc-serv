## API

### Add new app
```
POST /agora/app
{
    "app_id": "",
    "app_cert": "",
}
==>
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
==>
{
    "token": "result"
}
```