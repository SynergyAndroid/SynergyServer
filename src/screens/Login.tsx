import React from "react";
import { View,Text,StyleSheet, Button} from "react-native";
import { useNavigation } from '@react-navigation/native';



const Login=() => {
    const navigation = useNavigation();
    const goToHome = () => 
        navigation.navigate('홈');

    return (
        <View style={styles.container}>
            <Text>Login</Text>
            <Button onPress={goToHome} title="홈버튼"></Button>

        </View>

    )

}

const styles = StyleSheet.create({
    container:{
        margin:"auto"
    }
    
});

export default Login;