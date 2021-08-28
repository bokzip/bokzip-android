package com.unionz.bokzip.util

class StrUtil {

    // 괄호문자열 제거
    fun removeBracket(str:String) : String{
        var result = ""

        var stack = arrayListOf<Char>()
        for(c in str){
            if(c == '('){
                stack.add(c)
            } else if(c==')'){
                stack.removeAt(stack.size - 1)
            }
            if(stack.isEmpty() && c != ')'){
                result += c;
            }
        }
        return result
    }

    // 띄어쓰기 및 줄바꿈 조정
    fun setNewLine(str: String): String {
        if (str.length <= 8) { // 8글자 이하인 경우 조정 안함
            return str
        }

        var tmpStr = str
        if(str.indexOf('(') != -1) { // 괄호문자열이 포함된 경우 해당문자열을 제거
            tmpStr = removeBracket(str)
        }

        var title = ""
        val words = tmpStr.split(" ")
        if (words.size == 0) return title
        for (i in 0..words.size - 2) {
            if (words[i].length + words[i + 1].length >= 8) { // 현재 단어가 다음 단어를 합쳤을 때 8글자 이상인 경우 줄바꿈
                title += words[i] + "\n"
            } else { // 그렇지 않은 경우 띄어쓰기
                  title += words[i] + " "
            }
        }

        // 마지막 줄바꿈 문자부터 끝까지의 문자열이 8글자를 초과한 경우 줄바꿈 처리
        val lastIdx = title.lastIndexOf('\n')
        if(lastIdx == -1){
            val tmpTitle = title + words[words.size - 1]
            if (tmpTitle.length > 8){
               return title +"\n" + words[words.size - 1]
            }
        }
        else{
            val tmpTitle = title.substring(lastIdx+1) + words[words.size - 1]
            if (tmpTitle.length > 8){
                return title.substring(lastIdx+1) + "\n" + words[words.size - 1]
            }
        }

        title += words[words.size - 1]
        return title
    }
}