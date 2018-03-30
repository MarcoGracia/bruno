# clean-acme-inc

## Description

## Contents
- [Description](#description)
- [How to Use](#how-to-use)
- [Usage and Examples](#api)

## How To Use

## api

* Register device:
```c
$ curl -X PUT -u "<app_key>:<app_secret>" http://<server_ip>:9000/api/device_tokens/<device_token>
```

* Get user
```c
$ curl -X POST -u "<app_key>:<app_master_secret>" -H "Content-Type: application/json" --data '{"data": {"a_key": "hello world!"}}' http://<server_ip>:9000/api/push/broadcast

```

* Postman collection: [https://www.getpostman.com/collections/3185d43ded10e838172e](https://www.getpostman.com/collections/3185d43ded10e838172e)

## TODOS