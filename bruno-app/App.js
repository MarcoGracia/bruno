import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Notifications } from 'expo';
import registerForPushNotificationsAsync from './src/notifications';
import Logo from './src/Logo';

export default class App extends React.Component {
  state = { notification: {} };

  async componentWillMount() {
    await registerForPushNotificationsAsync();
    this._notificationSubscription = Notifications.addListener(this._handleNotification);
  };

  _handleNotification = (notification) => {
    this.setState({ notification: notification });
  };

  render() {
    console.log(this.state);

    return (
      <View style={styles.container}>
        <Logo />
        <Text>WHO DAT SIGNING UP</Text>

        <Text>Origin: {this.state.notification.origin}</Text>
        <Text>Data: {JSON.stringify(this.state.notification.data)}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'flex-start',
  },
});
