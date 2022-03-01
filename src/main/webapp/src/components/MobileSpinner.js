import {
    Button,
    Input,
    HStack,
    useNumberInput
  } from '@chakra-ui/react';

const MobileSpinner = props => {
    const { getInputProps, getIncrementButtonProps, getDecrementButtonProps } =
      useNumberInput({
        step: 1,
        defaultValue: 0,
        precision: 0,
        ...props
      })
  
    const inc = getIncrementButtonProps()
    const dec = getDecrementButtonProps()
    const input = getInputProps({ isReadOnly: true })
  
    return (
      <HStack maxW='320px'>
        <Button {...inc}>+</Button>
        <Input {...input} />
        <Button {...dec}>-</Button>
      </HStack>
    )
}

export default MobileSpinner