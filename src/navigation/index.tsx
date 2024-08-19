import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Home from '../screens/Home';
import SignUp from '../screens/SignUp';
import Login from '../screens/Login';
import NewPost from '../screens/NewPost';
import BottomBar from '../components/bottom';
import Community from '../screens/Community';
import Profile from '../screens/Profile';
import Chat from '../screens/Chat';
import ChatList from '../screens/ChatList';
import PostDetail from '../screens/PostDetail';
import OnboardingNavigator from '../navigation/onboardingNavigator'; // 새로 추가된 부분

const Stack = createNativeStackNavigator();

function MainStack() {
  return (
    <>
      <Stack.Navigator initialRouteName="홈">
        <Stack.Screen name="홈" component={Home} options={{ headerShown: false }} />
        <Stack.Screen name="회원가입" component={SignUp} options={{ headerShown: false }} />
        <Stack.Screen name="로그인" component={Login} options={{ headerShown: false }} />
        <Stack.Screen name="새로운 글 작성" component={NewPost} />
        <Stack.Screen name="커뮤니티" component={Community} />
        <Stack.Screen name="상세글 보기" component={PostDetail} />
        <Stack.Screen name="프로필" component={Profile} />
        <Stack.Screen name="채팅" component={Chat} />
        <Stack.Screen name="채팅목록" component={ChatList} />
      </Stack.Navigator>
      <BottomBar />
    </>
  );
}

function Appnavigation() {
  const [isFirstLaunch, setIsFirstLaunch] = React.useState(true); // 첫 실행 여부를 관리

  React.useEffect(() => {
    // 첫 실행 여부를 저장하고 필요한 경우 이를 변경
    // 실제 앱에서는 AsyncStorage 등을 사용해 관리할 수 있습니다.
    const checkFirstLaunch = async () => {
      // 로직에 따라 첫 실행 여부를 결정
      // setIsFirstLaunch(false);
    };

    checkFirstLaunch();
  }, []);

  return (
    <NavigationContainer>
      {isFirstLaunch ? <OnboardingNavigator /> : <MainStack />} 
    </NavigationContainer>
  );
}

export default Appnavigation;
