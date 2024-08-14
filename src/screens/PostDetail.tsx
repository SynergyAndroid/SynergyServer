import React, { useState } from 'react';
import { View, Text, StyleSheet, ScrollView, TextInput, TouchableOpacity } from 'react-native';

interface PostDetailProps {
  route: any;
}

const PostDetail: React.FC<PostDetailProps> = ({ route }) => {
  const { post } = route.params;
  const [newComment, setNewComment] = useState('');
  const [comments, setComments] = useState(post.comments);

  const handleAddComment = () => {
    if (newComment.trim()) {
      setComments([...comments, newComment]);
      setNewComment('');
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.postContainer}>
        <Text style={styles.title}>{post.name}</Text>
        <Text style={styles.subtitle}>{post.location} · {post.age}세</Text>
        <Text style={styles.content}>{post.content}</Text>
      </View>
      
      <View style={styles.divider} />
      
      <View style={styles.commentsContainer}>
        <Text style={styles.commentsTitle}>댓글 {comments.length}개</Text>
        {comments.map((comment: string, index: number) => (
          <View key={index} style={styles.comment}>
            <Text>{comment}</Text>
          </View>
        ))}
      </View>

      <View style={styles.inputContainer}>
        <TextInput
          style={styles.input}
          value={newComment}
          onChangeText={setNewComment}
          placeholder="댓글을 입력하세요"
        />
        <TouchableOpacity style={styles.button} onPress={handleAddComment}>
          <Text style={styles.buttonText}>댓글 달기</Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  postContainer: {
    marginBottom: 16,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  subtitle: {
    fontSize: 16,
    color: '#666',
    marginBottom: 16,
  },
  content: {
    fontSize: 18,
    marginBottom: 16,
  },
  divider: {
    height: 1,
    backgroundColor: '#ccc',
    marginVertical: 16,
  },
  commentsContainer: {
    marginBottom: 16,
  },
  commentsTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  comment: {
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: '#eee',
  },
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    borderTopWidth: 1,
    borderTopColor: '#eee',
    paddingTop: 16,
  },
  input: {
    flex: 1,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    padding: 8,
    marginRight: 8,
  },
  button: {
    backgroundColor: '#E0BACB',
    padding: 10,
    borderRadius: 4,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
});

export default PostDetail;
