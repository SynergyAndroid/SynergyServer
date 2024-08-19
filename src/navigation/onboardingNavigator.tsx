import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import OnboardingScreen from '../screens/onboarding/onboarding';

const Stack = createNativeStackNavigator();

function OnboardingNavigator() {
  return (
    <Stack.Navigator initialRouteName="Onboarding">
      <Stack.Screen name="Onboarding" component={OnboardingScreen} options={{ headerShown: false }} />
    </Stack.Navigator>
  );
}

export default OnboardingNavigator;
