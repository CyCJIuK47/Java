## Setup
This app uses a Gmail smtp server to send mails. Set `login` and `password` in `.env`.

Highly recommend not using a real password from your Gmail account. You can [**generate**](https://support.google.com/mail/answer/185833?hl=en) a 16-digit password for applications for development purposes.

Then run:
```bash
docker-compose up --build --detach
```

## Usage

To send mails use `POST` request:
```
http://localhost:8080/mails/send
```

```json
{
  "to": "example-to@gmail.com",
  "from": "example-from@gmail.com",
  "subject": "example",
  "text": "example"
}
```

