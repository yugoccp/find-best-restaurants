import React from 'react'
import {
  Center,
  GridItem,
  Stack,
  Heading,
  Image,
  Text,
  Spacer
} from '@chakra-ui/react'
import { StarIcon } from '@chakra-ui/icons'
import { formatMoney } from '../utils'
import { FaMapPin } from 'react-icons/fa'

const RestaurantItem = ({name, image, customerRating, cuisine, price, distance}) => 
<GridItem bg='white' borderRadius={7}>
    <Center>
        <Image src={image} alt={image} borderTopRadius={7} />
    </Center>
    
    <Heading size='md' color='gray.600'>{name}</Heading>

    {[...Array(customerRating).keys()].map(i => 
        <StarIcon key={i} w={4} h={4} color='yellow.500' />
    )}

    <Text fontSize={14} color='gray.600'>{cuisine}, {formatMoney(price)}</Text>
    
    <Stack direction='row' p={2}>
        <Spacer />
        <FaMapPin color='black' />
        <Text fontSize={14} color='gray.600'>{distance} miles</Text>
    </Stack>
</GridItem>

export default RestaurantItem