import React from 'react';
import { SafeAreaView, StyleSheet } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import Appnavigation from './src/navigation/index';

function App() {
  return (
    <NavigationContainer>
      <SafeAreaView style={styles.container}>
        <Appnavigation />
      </SafeAreaView>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default App;
