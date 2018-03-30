import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { StackNavigator } from 'react-navigation';
import { Notifications } from 'expo';
import registerForPushNotificationsAsync from './src/notifications';
import Logo from './src/Logo';
import Card from './src/Card';

class App extends React.Component {
  // hide nav header
  static navigationOptions = {
    header: null
  };

  state = { list: [] };

  async componentWillMount() {
    await registerForPushNotificationsAsync();
    this._notificationSubscription = Notifications.addListener(this._handleNotification);
  };

  _handleNotification = (notification) => {
    const list = this.state.list.slice().concat([ notification ]);

    this.setState({ list });
  };

  render() {
    console.log(this.state);

    return (
      <View style={styles.container}>
        <View style={{ alignItems: 'center', marginBottom: 25 }}>
          <Logo />
          <Text>WHO DAT SIGNING UP</Text>
        </View>

        <View>
          {this.state.list.map((notification, i) => (
            <Card
              key={i}
              fullName={notification.data.fullName}
              timestamp={notification.data.timestamp}
              city={notification.data.city}
              light={Boolean(i % 2)}
            />
          ))}
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#20ab00',
    justifyContent: 'flex-start',
  },
});

export default StackNavigator({
  Home: { screen: App }
});
