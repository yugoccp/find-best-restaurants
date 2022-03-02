import React from 'react'
import {
  ChakraProvider,
  Box,
  Center,
  VStack,
  HStack,
  Grid,
  Button,
  Input,
  Text,
  Heading
} from '@chakra-ui/react'
import { SearchIcon } from '@chakra-ui/icons'
import MobileSpinner from './components/MobileSpinner'
import RestaurantItem from './components/RestaurantItem'
import { getBestRestaurants } from './api/restaurantService'
import { formatMoney, parseMoney } from './utils'
import theme from './theme'

const App = () => {
  
  const [name, setName] = React.useState('')
  const [cuisine, setCuisine] = React.useState('')
  const [price, setPrice] = React.useState('25')
  const [distance, setDistance] = React.useState('5')
  const [customerRating, setCustomerRating] = React.useState('3')
  const [restaurants, setRestaurants] = React.useState([])

  const handleNameChange = (event) => setName(event.target.value)
  const handleCuisineChange = (event) => setCuisine(event.target.value)

  const searchRestaurants = () => {
    getBestRestaurants({
      name,
      cuisine,
      price,
      distance,
      customerRating
    }).then(data => {
      setRestaurants(data)
    })
  }

  return (
    <ChakraProvider theme={theme}>
      <Center>
        <Box textAlign='center' fontSize='l' p={7} m={7} borderRadius='md' maxW={640} minH={500} bg='yellow.50'>
          
          <Heading size='lg' mb={5}>🍗🍟🍔 Find Best Restaurants 🍱🌮🍻</Heading>
          
          <Grid p={3}>
            <VStack spacing={8}>
              <Input
                placeholder='Enter Restaurant Name'
                onChange={handleNameChange}
                value={name}
              />
              
              <Input 
                  placeholder='Enter Cuisine' 
                  onChange={handleCuisineChange}
                  value={cuisine}/>

              <HStack >
                <Text mb='8px' color='gray.600'>Customer Rating</Text>
                <MobileSpinner 
                  min={1} 
                  max={5}
                  onChange={setCustomerRating}
                  value={customerRating} />
              </HStack>
              
              <HStack>
                <Text mb='8px' color='gray.600'>Distance</Text>
                <MobileSpinner 
                  min={1} 
                  max={10} 
                  onChange={setDistance}
                  value={distance}/>
              </HStack>
              
              <HStack>
                <Text mb='8px' color='gray.600'>Price</Text>
                <MobileSpinner 
                  min={10} 
                  max={50} 
                  onChange={(valueString) => setPrice(parseMoney(valueString))}
                  value={formatMoney(price)}
                />
              </HStack>

              <Button rightIcon={<SearchIcon />} colorScheme='blue' onClick={searchRestaurants}>Search</Button>
            </VStack>
          </Grid>
          
          <Grid 
              p={3} gap={2} 
              templateColumns='repeat(3 , 1fr)'>
            {
              restaurants.map(restaurant => 
                <RestaurantItem key={restaurant.name} {...restaurant} />
            )}
          </Grid>
        </Box>
      </Center>
    </ChakraProvider>
  )
}

export default App
