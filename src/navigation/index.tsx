import React from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
//import OnboardingNavigator from './OnboardingNavigator';
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
          // 일단 true 로 온보딩만 띄워놓기
          setIsFirstLaunch(true);
        }
      } catch (error) {
        console.error('Error checking first launch:', error);
      }
    };

    checkFirstLaunch();
  }, []);

  // 
  React.useEffect(() => {
    const resetOnboarding = async () => {
  
      //await AsyncStorage.removeItem('hasLaunched');
    };

    resetOnboarding();
  }, []);

  if (isFirstLaunch === null) {
    // 앱 초기화 중이므로 로딩 화면을 보여줄 수 있습니다.
    return null; // 또는 로딩 스피너 컴포넌트를 반환
  }

  return (
    <>
      <AppNavigator />
    </>
  );
}

export default Appnavigation;
