import React, { useEffect } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import * as KakaoLogin from '@react-native-seoul/kakao-login';
import { useNavigation } from '@react-navigation/native';

const Login: React.FC = () => {
  const navigation = useNavigation();

  useEffect(() => {
    const handleKakaoLogin = async () => {
        try {
          // 요청할 권한을 지정합니다. 여기서는 이메일과 프로필 정보를 요청합니다.
          const scopes = ['account_email', 'profile_nickname', 'profile_image'];
          const result = await KakaoLogin.login({ scopes });
          console.log('Login Success', JSON.stringify(result));
      
          // 로그인 성공 후 프로필 정보를 가져옵니다.
          const profile = await KakaoLogin.getProfile();
          console.log('GetProfile Success', JSON.stringify(profile));
      
          // 로그인 후 홈 화면으로 이동합니다.
          navigation.navigate('홈');
        } catch (error) {
          console.error('Login Failed', error);
          // 로그인 실패 시 처리 (예: 에러 메시지 표시)
        }
      };
      
    // 컴포넌트가 마운트될 때 카카오 로그인을 실행합니다.
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
