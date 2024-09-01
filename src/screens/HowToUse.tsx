import React from "react";
import { View,Text,Image, StyleSheet,ScrollView } from "react-native";


const HowToUse =() => {
    return (
        <ScrollView style={{backgroundColor:"white"}}>
            <Image source={require('../components/assets/images/light.png')} style={styles.lights}/>
            <Text style={styles.useText}>
                시너지 사용방법에 대해 
                알려드릴게요.

            </Text>
        </ScrollView>

    );
};


const styles = StyleSheet.create({

    lights:{
        marginTop:50,
        width: 150,
        height: 150,
        alignItems:"center",
        justifyContent:"center",
        marginVertical:30,
        marginHorizontal:120,
    },
    useText:{
        fontSize:20,
        textAlign:"center",
        color:"black",
        marginTop:30,
        //fontWeight:"bold",
        
    }


});

export default HowToUse;