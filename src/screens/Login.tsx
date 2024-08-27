import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';


const Login = () => {
  const [inputId, setInputId] = useState('');
  const [inputPassword, setInputPassword] = useState('');
  const [error, setError] = useState('');
  const navigation = useNavigation();

  
  const storedId = 'testUser';
  const storedPassword = '1234';

  const handleLogin = () => {
    if (inputId === storedId && inputPassword === storedPassword) {
      // 로그인 성공 시 홈 화면으로 이동
      navigation.navigate('홈');
    } else {
      // 로그인 실패 시 오류 메시지 설정
      setError('아이디 또는 비밀번호가 올바르지 않습니다.');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>로그인</Text>
      
      <TextInput
        style={styles.input}
        placeholder="아이디"
        value={inputId}
        onChangeText={setInputId}
      />
      
      <TextInput
        style={styles.input}
        placeholder="비밀번호"
        value={inputPassword}
        onChangeText={setInputPassword}
        secureTextEntry={true}
      />

      {error ? <Text style={styles.errorText}>{error}</Text> : null}
      <View style={styles.findLogin}>
        <Text>아이디 찾기   </Text>
        <Text>비밀번호찾기</Text>
      </View>

        <TouchableOpacity style={styles.buttonStyle} onPress={handleLogin} >
            <Text style={styles.buttonText}>다음</Text>
      </TouchableOpacity>
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
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 10,
  },
  errorText: {
    color: 'red',
    marginBottom: 10,
  },
  buttonStyle:{
    position: 'absolute',
    bottom: 20,
    left: 20,
    right: 20,
    backgroundColor: '#005F40',
    paddingVertical: 15,
    alignItems: 'center',
    borderRadius: 5,
    
  },
  buttonText:{
    color:"white",
  },
  findLogin:{
    flexDirection:"row",
    justifyContent:"flex-end"
  }
});

export default Login;
