import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Home from '../screens/Home';
import NewPost from '../screens/NewPost';
import BottomBar from '../components/bottom';
import Community from '../screens/Community';
import Profile from '../screens/Profile';
import Chat from '../screens/Chat';
import ChatList from '../screens/ChatList';
import PostDetail from '../screens/PostDetail';

const Stack = createNativeStackNavigator();

function AppNavigator() {
  return (
    <>
      <Stack.Navigator initialRouteName="홈">
        <Stack.Screen name="홈" component={Home} options={{ headerShown: false }} />
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

export default AppNavigator;
