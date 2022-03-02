import { extendTheme } from '@chakra-ui/react'

const theme = {
  styles: {
    global: {
      body: {
        bg: 'red.400'
      },
    }
  },
  useSystemColorMode: true,
  initialColorMode: 'light',
}

const customTheme = extendTheme(theme)

export default customTheme