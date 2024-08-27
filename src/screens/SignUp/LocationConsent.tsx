// src/screens/SignUp/LocationConsent.tsx
import React from 'react';
import { View, Text, Button, StyleSheet,TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';


const LocationConsent = () => {
  const navigation = useNavigation();

  const handleConsent = (consent: boolean) => {
    navigation.navigate('SignUpComplete');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>위치정보 동의</Text>
      <Text style={styles.description}>위치정보 사용에 동의하시겠습니까?</Text>
      <View style={styles.buttonContainer}>
        <TouchableOpacity onPress = {() => handleConsent(true)} >
            <Text>동의</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress = {() => handleConsent(false)} >
            <Text>거부</Text>
        </TouchableOpacity>

      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
  },
  title: {
    flex:1,
    fontSize: 24,
    fontWeight: 'bold',
  },
  description: {
    flex:1,
    fontSize:20,
  },
  buttonContainer: {
    flex:2,
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
});

export default LocationConsent;