package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

            String[]s = signatureString.split(" ");
            if (s.length<2){
                throw new UnsupportedOperationException();
            }
            for(int i = 0; i < s.length;i++){
                System.out.println(s[i]);
            }
             boolean hasAccessModifier = false;
            List<MethodSignature.Argument>arguments=new ArrayList<>();
            String accessModifier="", methodName, returnType;
            if (s[0].equals("private")||s[0].equals("public")||s[0].equals("protected")){
                hasAccessModifier=true;
                accessModifier = s[0];
                returnType=s[1];
                methodName=s[2].split("\\(")[0];
            }
            else{
                returnType=s[0];
                methodName=s[1].split("\\(")[0];
            }
            //first arg
            String[]splitString = signatureString.split("\\(");
            String[]argumentsArray=splitString[1].split(", ");
            if(!splitString[1].equals(")")){
                for(int i =0; i <argumentsArray.length-1;i++ ){
                    arguments.add(new MethodSignature.Argument(argumentsArray[i].split(" ")[0],argumentsArray[i].split(" ")[1]));
                }
                arguments.add(new MethodSignature.Argument(argumentsArray[argumentsArray.length-1].split(" ")[0],argumentsArray[argumentsArray.length-1].split(" ")[1].split("\\)")[0]));
            }


            MethodSignature methodSignature=new MethodSignature(methodName, arguments);
            if (hasAccessModifier) methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);

        return methodSignature;

    }

}
