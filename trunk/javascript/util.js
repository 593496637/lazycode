
/**
 * @author hxl
 */
if (typeof window['HXL'] == 'undefined') 
    HXL = {};
(function(){
    HXL.util = {};
    HXL.util = {
        /**
         * @param {Object} str Ŀ���ַ���
         * @param {Object} oldStr ��Ҫ�滻�����ַ���������һ������
         * @param {Object} newStr ��������ַ����������ַ���������һ������
         */
        _replaceAll: function(str, oldStr, newStr){
            if (oldStr instanceof Array) {
                var len = oldStr.length;
                if (newStr instanceof Array) {
                    if (newStr.length == len) {
                        for (i = 0; i < len; i++) {
                            str = str.replace(/oldStr[i]/g, newStr[i]);
                        }
                    }
                    else {
                        alert("newStr is Invalid argument.");
                        throw new Error("newStr is Invalid argument.");
                    }
                }
                else {
                    for (i = 0; i < len; i++) {
                        str = str.replace(/oldStr[i]/g, newStr);
                    }
                }
                return str;
            }
            else {
                if (newStr instanceof Array) 
                    throw new Error("newStr is Invalid argument");
                return str.replace(/oldStr/g, newStr);
            }
            
        },
        /**
         * ���idԪ�ض���
         * @param {Object} id
         */
        _$: function(id){
            return document.getElementById(id);
        },
        _removeAttribute: function(obj, attributeName){
            // alert(obj.getAttribute('id'));
            obj.setAttribute(attributeName, '');
        },
        _addAttribute: function(obj, attributeName, attributeValue){
            obj.setAttribute(attributeName, attributeValue);
        }
		/**
		 *��һ��number����ǿ��ת��Ϊint
		 */
		_caseInt : function(v){
			if(typeof v != "number")
			   return v;
			return v >> 0;
		}
    }
})();
