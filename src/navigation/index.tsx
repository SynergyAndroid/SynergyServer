import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import OnboardingNavigator from './OnboardingNavigator';
import AppNavigator from './AppNavigator';

function Appnavigation() {
  const [isFirstLaunch, setIsFirstLaunch] = React.useState(null);

  React.useEffect(() => {
    const checkFirstLaunch = async () => {
      try {
        const hasLaunched = await AsyncStorage.getItem('hasLaunched');
        if (hasLaunched === null) {
          setIsFirstLaunch(true);
          await AsyncStorage.setItem('hasLaunched', 'true');
        } else {
          setIsFirstLaunch(false);
        }
      } catch (error) {
        console.error('Error checking first launch:', error);
      }
    };

    checkFirstLaunch();
  }, []);

  if (isFirstLaunch === null) {
    // 앱 초기화 중이므로 로딩 화면을 보여줄 수 있습니다.
    return null; // 또는 로딩 스피너 컴포넌트를 반환
  }

  return (
    <NavigationContainer>
      {isFirstLaunch ? <OnboardingNavigator /> : <AppNavigator />}
    </NavigationContainer>
  );
}

export default Appnavigation;
