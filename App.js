import { StyleSheet, FlatList, View, Text } from 'react-native';
import React, { useState, useEffect } from "react";
import HeadlineDetail from './src/components/HeadlineDetail';
import { NativeModules } from 'react-native';

const App = () => {

  const { ApiModule } = NativeModules;
  const [allItems, setAllItems] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    ApiModule.fetchApiData("MyMayoClinic", getCurrentDate())
      .then(jsonData => { updateItemsFromResult(jsonData) })
      .catch(error => { console.error(error) });
  }, [])

  function getCurrentDate() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  function updateItemsFromResult(jsonData) {
    const packages = JSON.parse(jsonData).Packages;
    setAllItems(packages.map((packageItem) => packageItem.Items).flat());
    setIsLoading(false);
  }

  return (
    <View style={styles.container}>
      {isLoading ? (<Text>Loading...</Text>) : (
        <FlatList
          data={allItems}
          keyExtractor={(item) => item.Id}
          renderItem={({ item }) => ( <HeadlineDetail item={item}/> )}
          showsVerticalScrollIndicator={false} 
        />
      )}
    </View>
  );
  
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  imageStyle: {
    fontSize: 24,
    height: 80,
    width: 80,
  },
  title: {
    fontSize: 24,
    marginTop: 80,
    marginBottom: 80,
  },
  buttonText: {
    fontSize: 18,
    color: 'blue',
  },
  resultText: {
    fontSize: 20,
    marginBottom: 10,
  },
});

export default App;