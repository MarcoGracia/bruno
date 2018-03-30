import React from 'react';
import { View, Text } from 'react-native';

export default ({ fullName, timestamp, city, light }) => {
  const bgcolor = light ? '#7cc869' : '#3c9e26';
  const style = {
    backgroundColor: bgcolor,
    alignSelf: 'stretch',
    padding: 25
  };

  return (
    <View style={style}>
      {console.log(fullName, timestamp)}
      <Name title={fullName} />
      <Text>uit</Text>
      <Text style={{ color: 'white', fontSize: 20 }}>{city}</Text>
      <Text style={{ color: 'white' }}>{timestamp}</Text>
    </View>
  );
};

const Name = ({ title }) => <Text style={{ fontSize: 45, fontWeight: 'bold', color: 'white' }}>{title}</Text>;
