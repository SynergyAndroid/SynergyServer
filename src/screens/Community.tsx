import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';
import { useNavigation } from '@react-navigation/native';


// 인터페이스 지정하기 
interface Post {
  id: number;
  name: string;
  age: number;
  location: string;
  content: string;
  comments: string[];
}

const Community: React.FC = () => {
  const navigation = useNavigation();
  const [posts] = useState<Post[]>([
    {
      id: 1,
      name: '해롱이',
      age: 58,
      location: '수원시 팔달구',
      content: '이번주 목요일 저녁 8시에 영동시장 같이 가요 !!',
      comments: ['쪽지드릴게요!', '저도 같이 가고 싶어요.'],
    },
    {
      id: 2,
      name: '한태진',
      age: 60,
      location: '수원시 권선구',
      content: '내일 같이 낚시하러 가실 분 계신가요? 수원시청 앞에서 내일 오전 10시 출발입니다.',
      comments: ['낚시 좋아하시나봐요!', '날씨가 좋아서 낚시하기 좋겠어요.'],
    },
  ]);

  const renderPost = (post: Post) => (
    <TouchableOpacity
      key={post.id}
      style={styles.postContainer}
      onPress={() => navigation.navigate('PostDetail', { post })}
    >
      <View style={styles.profileContainer}>
        <Icon name="user" size={24} style={styles.profileIcon} />
        <View>
          <Text style={styles.nameAge}>{post.name} / {post.age}세</Text>
          <Text style={styles.location}>{post.location}</Text>
        </View>
      </View>
      <Text style={styles.content}>{post.content}</Text>
    </TouchableOpacity>
  );

  return (
    <ScrollView style={styles.container}>
      <Text style={styles.title}>게시판 보기</Text>
      {posts.map(renderPost)}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 30,
    marginTop:10,
    marginHorizontal:'auto'
  },
  postContainer: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    padding: 10,
    marginBottom: 10,
  },
  profileContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 5,
  },
  profileIcon: {
    marginRight: 10,
  },
  nameAge: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  location: {
    fontSize: 14,
    color: '#666',
  },
  content: {
    fontSize: 16,
    marginVertical: 10,
  },
});

export default Community;
