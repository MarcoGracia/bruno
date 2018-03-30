import React from 'react';
import { View, Text } from 'react-native';

export default ({ fullName, timestamp, city, light }) => {
  const bgcolor = light ? '#43b02a' : '#358c22';
  const style = {
    backgroundColor: bgcolor,
    alignSelf: 'stretch',
    padding: 25
  };
  const date = new Date();
  const hour = `${date.getHours()}:${date.getMinutes()}`;

  return (
    <View style={style}>
      <Name title={fullName} />

      <Text style={{ color: 'white' }}>uit</Text>
      <Text style={{ color: 'white', fontSize: 30 }}>{city}</Text>
      <Text style={{ color: '#8ec881', marginTop: 35 }}>
        {hour}
      </Text>
    </View>
  );
};

const Name = ({ title }) => <Text style={{ fontSize: 45, fontWeight: 'bold', color: 'white' }}>{title}</Text>;
