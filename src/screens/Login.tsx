import React, { useEffect } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import * as KakaoLogin from '@react-native-seoul/kakao-login';
import { useNavigation } from '@react-navigation/native';

const Login: React.FC = () => {
  const navigation = useNavigation();

  useEffect(() => {
    const handleKakaoLogin = async () => {
        try {
          const scopes = ['account_email', 'profile_nickname', 'profile_image'];
          const result = await KakaoLogin.login({ scopes });
          console.log('Login Success', JSON.stringify(result));
      
          const profile = await KakaoLogin.getProfile();
          console.log('GetProfile Success', JSON.stringify(profile));
      
  
          navigation.navigate('홈');
        } catch (error) {
          console.error('Login Failed', error);
        }
      };
      
    handleKakaoLogin();
  }, [navigation]);

  return (
    <View style={styles.container}>
      <Text style={styles.text}>카카오 로그인 중...</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 18,
    color: '#333',
  },
});

export default Login;
