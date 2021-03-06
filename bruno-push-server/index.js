const Expo = require('expo-server-sdk');
const Express = require('express');
const bodyParser = require('body-parser');

const app = Express();
const port = 8000;

app.use(bodyParser.json());

app.post('/register', async (req, res) => {
  console.log(`registering ${req.body.token.value}...`)

  if (somePushTokens.includes(req.body.token.value)) {
    console.log('already registered');
    res.status(400).send('already registered');
    return;
  }

  somePushTokens.push(req.body.token.value)
  console.log('registered!')
  res.status(200).send('ok')
});

app.post('/signup', async (req, res) => {
  const data = req.body;
  console.log(`signup data ${JSON.stringify(data)}`)
  const message = `${data.fullName} has just signed in!`;
  console.log(message)

  pushStuff(message, data);
  res.status(200).send('ok');
})


app.listen(port);

// Create a new Expo SDK client
let expo = new Expo();



let somePushTokens = [];


const pushStuff = (message, data) => {
  
  console.log(somePushTokens)
  console.log(message)
  
  // Create the messages that you want to send to clents
  let messages = [];
  for (let pushToken of somePushTokens) {
    // Each push token looks like ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]

    // Check that all your push tokens appear to be valid Expo push tokens
    if (!Expo.isExpoPushToken(pushToken)) {
      console.error(`Push token ${pushToken} is not a valid Expo push token`);
      continue;
    }

    // Construct a message (see https://docs.expo.io/versions/latest/guides/push-notifications.html)
    messages.push({
      to: pushToken,
      sound: 'default',
      body: message,
      data,
    })
  }

  // The Expo push notification service accepts batches of notifications so
  // that you don't need to send 1000 requests to send 1000 notifications. We
  // recommend you batch your notifications to reduce the number of requests
  // and to compress them (notifications with similar content will get
  // compressed).
  let chunks = expo.chunkPushNotifications(messages);

  (async () => {
    // Send the chunks to the Expo push notification service. There are
    // different strategies you could use. A simple one is to send one chunk at a
    // time, which nicely spreads the load out over time:
    for (let chunk of chunks) {
      try {
        let receipts = await expo.sendPushNotificationsAsync(chunk);
        console.log(receipts);
      } catch (error) {
        console.error(error);
      }
    }
  })();
}
