// JavaScript Document
    if (typeof window['HXL'] == 'undefined') 
                HXL = {};
            (function(){
                HXL.tab = {};
                HXL.tab._encodeHtml = false;/*�Ƿ��ʽ��html��ǩ*/
                HXL.tab._contentId = '';/*����ʾ������Ԫ�ص�idֵ*/
                HXL.tab.setEncodeHtml = function(b){
                    HXL.tab._encodeHtml = b;
                };
                HXL.tab.setContentId = function(id){
                    HXL.tab._contentId = id;
                };
				/**
				 *�ı���ʾ����
				 *@contentId ��ʾ����Ԫ�ص�idֵ
				 *@content ����˵�ַ������ߺ���
				 *   ˵����һ������content���������ٵ���change����ʱ��contentΪ�ַ���
				 */
                HXL.tab.change = function(contentId, content, encodeHtml){
                    var contentStr = '';
                    if (typeof content == 'function') {
                        // if (typeof content.callback == 'undefined') 
                        content();
                        return;
                        //   else {
                        //  
                        //   }
                    
                    }
                    else 
                        if (typeof content == 'string') 
                            contentStr = content;
                    if (typeof encodeHtml == 'undefined' || encodeHtml == null) 
                        encodeHtml = this._encodeHtml;
                    
                    if (!!encodeHtml) {
                        content = HXL.util._replaceAll(content, [/&/g, /"/g, /</g, />/g], ['&amp', '&quot;', '&lt;', '&gt;']);
                    }
					if(contentId == null || typeof contentId == 'undefined')
					    contentId = this._contentId;
						
                    HXL.util._$(contentId).innerHTML = contentStr;
                };
                HXL.tab.event = function(ulId, contentId, cssId, content, encodeHtml){
                
                    HXL.util._$(ulId).onmouseover = function(e){
                        var obj = HXL.util._$(cssId);//���Ŀǰ��ʾ�˵�����
                        e = e || window.event;
                        var target = e.srcElement || e.target;
                        
                        if (typeof target.nodeName != 'undefined' && (target.nodeName.toLowerCase() == 'li' || target.nodeName.toLowerCase() == 'ol')) {
                            HXL.util._removeAttribute(obj, 'id');//�Ƴ�Ŀǰ��ʾ�˵������cssid
                            HXL.util._addAttribute(target, 'id', cssId);//����ǰ���ͣ���Ĳ˵����cssid
                            // alert(typeof content);
                            //if (content != null || typeof content != 'undefined') 
                            HXL.tab.change(contentId, content, encodeHtml);//�ı���ʾ������
                            //  else 
                            //    if (typeof content == 'function') 
                            //     content();
                            //    else 
                            //   if (typeof content == 'object') 
                            //       new content();
                        }
                    };
                }
            })();