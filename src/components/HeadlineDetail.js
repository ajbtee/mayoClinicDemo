import React, { PureComponent } from "react";
import { View, Image, Text, StyleSheet, TouchableOpacity, Linking } from 'react-native';

/**
 * Component for displaying a headline item's details.
 */
class HeadlineDetail extends PureComponent {
  
  /**
   * Handles the press event when the user taps on the headline item.
   * Opens the URL associated with the headline.
   */
  handlePress = () => {
    Linking.openURL(this.props.item.Url);
  };

  render() {
    const { item } = this.props;
    const imageUri = item.Images.find(image => image.Type === 'DAILYCONTENTLISTIMAGEURL').Value;
    const categoryName = item.Category.Name;
    const headlineName = item.Name;

    return (
      <TouchableOpacity onPress={this.handlePress} style={styles.containerStyle}>
        <Image style={styles.imageStyle} source={{ uri: imageUri }} />
        <View style={styles.textContainerStyle}>
          <Text style={styles.categoryNameStyle}>{categoryName.toUpperCase()}</Text>
          <Text style={styles.headlineNameStyle}>{headlineName}</Text>
        </View>
      </TouchableOpacity>
    );
  }
}

const styles = StyleSheet.create({
  containerStyle: {
    margin: 24,
    elevation: 12,
    borderRadius: 16,
  },
  imageStyle: {
    width: 325,
    height: 240,
    borderTopLeftRadius: 16,
    borderTopRightRadius: 16,
  },
  textContainerStyle: {
    width: 325,
    height: 135,
    paddingTop: 8,
    paddingLeft: 12,
    paddingRight: 12,
    backgroundColor: 'white',
    borderBottomLeftRadius: 16,
    borderBottomRightRadius: 16,
  },
  categoryNameStyle: {
    color: '#333',
    fontSize: 14,
    marginBottom: 2,
  },
  headlineNameStyle: {
    color: '#333',
    fontSize: 24,
    fontWeight: 'bold',
  },
});

export default HeadlineDetail;