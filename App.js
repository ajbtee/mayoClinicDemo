import { StyleSheet, FlatList, View, Text } from 'react-native';
import React, { useState, useEffect } from "react";
import HeadlineDetail from './src/components/HeadlineDetail';
import { NativeModules } from 'react-native';

/**
 * Functional component representing the main application.
 */
const App = () => {

  // Access the ApiModule from NativeModules
  const { ApiModule } = NativeModules;

  // State variables to hold API data and loading state
  const [allItems, setAllItems] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  // Effect hook to fetch data once the component mounts
  useEffect(() => {
    // Fetch API data using the ApiModule and the current date
    ApiModule.fetchApiData("MyMayoClinic", getCurrentDate())
      .then(jsonData => { updateItemsFromResult(jsonData) })
      .catch(error => { console.error(error) });
  }, [])

  /**
   * Function to get the current date in 'YYYY-MM-DD' format.
   * @returns {string} Current date in 'YYYY-MM-DD' format.
   */
  function getCurrentDate() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  /**
   * Function to update the component state with data from the API result.
   * @param {string} jsonData - JSON data from the API.
   */
  function updateItemsFromResult(jsonData) {
    const packages = JSON.parse(jsonData).Packages;
    setAllItems(packages.map((packageItem) => packageItem.Items).flat());
    setIsLoading(false);
  }

  return (
    <View style={styles.containerStyle}>
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
  containerStyle: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: '#eee',
  }
});

export default App;