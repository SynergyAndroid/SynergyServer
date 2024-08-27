import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Home from '../screens/Home';
import NewPost from '../screens/NewPost';
//import BottomBar from '../components/bottom';
import Community from '../screens/Community';
import Profile from '../screens/Profile';
import Chat from '../screens/Chat';
import ChatList from '../screens/ChatList';
import PostDetail from '../screens/PostDetail';
import OnboardingScreen from '../screens/onboarding/Onboarding';
import TermsAgreement from '../screens/SignUp/TermsAgreements';
import UserInfo from '../screens/SignUp/UserInfo';
import LocationConsent from '../screens/SignUp/LocationConsent';
import SignUpComplete from '../screens/SignUp/SignUpComplete';
import Login from '../screens/Login';

const Stack = createNativeStackNavigator();

function AppNavigator() {
  return (
    <>
      <Stack.Navigator initialRouteName="온보딩화면">
        <Stack.Screen name="온보딩화면" component={OnboardingScreen} options={{headerShown:false}} />
        <Stack.Screen name="홈" component={Home} options={{ headerShown: false }} />
        <Stack.Screen name="새로운 글 작성" component={NewPost} />
        <Stack.Screen name="커뮤니티" component={Community} />
        <Stack.Screen name="상세글 보기" component={PostDetail} />
        <Stack.Screen name="프로필" component={Profile} />
        <Stack.Screen name="채팅" component={Chat} />
        <Stack.Screen name="채팅목록" component={ChatList} />
        <Stack.Screen name="TermsAgreement" component={TermsAgreement} options={{ headerShown: false }} />
        <Stack.Screen name="UserInfo" component={UserInfo} options={{ headerShown: false }} />
        <Stack.Screen name="LocationConsent" component={LocationConsent} options={{ headerShown: false }} />
        <Stack.Screen name="SignUpComplete" component={SignUpComplete} options={{ headerShown: false }} />
        <Stack.Screen name="Login" component={Login} options={{ headerShown: false }} />
      </Stack.Navigator>
  
    </>
  );
}

export default AppNavigator;
