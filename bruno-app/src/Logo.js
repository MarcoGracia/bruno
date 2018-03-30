import React from 'react';
import { View, Image } from 'react-native';
import logo from '../assets/vandebron.png';

const style = {
  flex: 0,
  marginTop: 45
};

export default () => (
  <View style={style}>
    <Image source={logo} />
  </View>
);
