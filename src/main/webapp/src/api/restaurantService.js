import { faker } from '@faker-js/faker'

export const getBestRestaurants = (searchParam) => {
    return fetch('/restaurant', {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
          'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(searchParam)
      })
      .then(response => response.json())
      .then(data => {
        const restaurantsData = data.map(restaurant => ({
          image: faker.image.animals(320, 240, true),
          ...restaurant
        }))
        return restaurantsData
      })
}