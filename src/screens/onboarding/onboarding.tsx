import React, { useState } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity } from 'react-native';
import PagerView from 'react-native-pager-view';
import { useNavigation } from '@react-navigation/native';

const OnboardingScreen: React.FC = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const navigation = useNavigation();

  const onboardingData = [
    {
      title: '시니어들의 커뮤니티 형성',
      text: '시니어들을 위한! 시니어들에 의한! 시니어 맞춤 커뮤니티를 만들어 다른 사람들과의 모임을 즐겨보세요',
      image: require('../../components/assets/images/friends.png')
    },
    {
      title: '내 근처 기반 장소 추천',
      text: '내 근처에서 어디를 갈까 고민한 적 있나요? 시:너지는 위치기반으로 사용자의 맞춤장소를 추천해드립니다!',
      image: require('../../components/assets/images/travelMan.png')
    },
    {
      title: '커뮤니티 검증',
      text: '특정 종교 권유 등 목적이 불건전한 커뮤니티 게시글은 삭제되는 기능이 있습니다. 깨끗하고 검증된 커뮤니티를 만나보세요.',
      image: require('../../components/assets/images/studying.png')
    },
  ];

  const handlePageChange = (event: any) => {
    setCurrentPage(event.nativeEvent.position);
  };

  const handleLoginPress = () => {
    navigation.navigate("Login");
  };

  const handleSignUpPress = () => {
    navigation.navigate('SignUp');
  }

  return (
    <View style={styles.container}>
      <PagerView
        style={styles.pagerView}
        initialPage={0}
        onPageSelected={handlePageChange}
      >
        {onboardingData.map((item, index) => (
          <View key={index} style={styles.page}>
            <Image source={item.image} style={styles.image} />
            <Text style={styles.title}>{item.title}</Text>
            <Text style={styles.text}>{item.text}</Text>
          </View>
        ))}
      </PagerView>
      <View style={styles.indicatorContainer}>
        {onboardingData.map((_, index) => (
          <View
            key={index}
            style={[
              styles.indicator,
              index === currentPage && styles.activeIndicator,
            ]}
          />
        ))}
      </View>
      <TouchableOpacity style={styles.loginButton} onPress={handleLoginPress}>
        <Text style={styles.loginButtonText}>로그인</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={handleSignUpPress}>
            <Text style={styles.signUpText}>어플이 처음이신가요?</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-between',
    paddingVertical: 20,
  },
  pagerView: {
    flex: 1,
  },
  page: {
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 40,
  },
  image: {
    width: 250,
    height: 250,
    marginTop:50,
    resizeMode: 'contain',
    marginBottom: 30,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    color:'black',
    textAlign: 'center',
    marginBottom: 10,
  },
  text: {
    fontSize: 16,
    color: '#777',
    textAlign: 'center',
  },
  indicatorContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 20,
  },
  indicator: {
    height: 10,
    width: 10,
    borderRadius: 5,
    backgroundColor: 'gray',
    marginHorizontal: 5,
  },
  activeIndicator: {
    backgroundColor: 'black',
  },
  loginButton: {
    backgroundColor: '#005F40',
    padding: 15,
    alignItems: 'center',
    marginHorizontal: 40,
    marginBottom: 10,
    borderRadius: 7,
  },
  loginButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color:'white'
  },
  signUpText: {
    fontSize: 14,
    textAlign:'center',
    marginBottom:20,
    color:'#005F40'


  }
});

export default OnboardingScreen;
