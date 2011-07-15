       /**
	    *@author hxl
		*@date 2011-03-11
		*sample��
		*HXL.drag("id" , [[0 , 10] , [0 , 50]] , true or false)();
		*ע�⣺����bug���߽�δ��⣬���г���ǰ��ʾ���ڵı߽磬Ȼ���϶��������Ĺ���������bug��
	    */
	   if (typeof window['HXL'] == 'undefined') 
            HXL = {};
        (function(){
            //     if (typeof HXL['drag'] == 'undefined') 
            //        HXL.drag = {};
            
            /**
             * drag�������涨����һ���϶��¼��������¼�
             * ��drag��������Ϊһ���հ���ʹ����ı�����Ϊ˽�еģ���粻�ܷ��ʣ����Ӱ�ȫ�����������
             * @param _moveId ��Ҫ��Ӹ����¼�Ԫ�ص�id��
			 * @param _range ��Ҫ��Ӧ�¼������ķ�Χ:
			      a)._range[0]��ʾx�������Сֵ�����ֵ_range[0][0]:��С;_range[0][1]: ���
		          b)._range[1]��ʾy�������Сֵ�����ֵ_range[1][0]:��С;_range[1][1]:���
             * @param _scrollflag �Ƿ񸡶�Ԫ�����Ź������Ĺ���������,true or false
             */
            HXL.drag = function(_moveId, _range, _scrollflag){
            
                if (arguments.length < 3) {
                    alert("error: arguments < 3");
                    throw new Error("arguments < 3");
                }
                
				/**
				 *�ж�_range�Ƿ�Ϊ����
				 */
                if (!_range instanceof Array && _range.length < 2 && _range[0].length < 2 && _range[1].length < 2) {
                    alert("error: _range is  illegality argument");
                    throw new Error("_range is  illegality argument");
                }
                
                var mousedownflag = 0;
                var x = 0;
                var y = 0;
                var mousedown = function(e){
                    //	alert("aa");
                    this.mousedownflag = 1;
                    //  this.x = e.offsetX ? e.offsetX : e.layerX;
                    //  this.y = e.offsetY ? e.offsetY : e.layerY;
                };
                var mousemove = function(e){
                
                    if (this.mousedownflag) {
                        //   alert(flag);
                        var movey = (e.offsetY ? e.offsetY : e.layerY) - this.y;
                        var movex = (e.offsetX ? e.offsetX : e.layerX) - this.x;
                        //   var scrollX = document.documentElement.scrollLeft;
                        // var scrollY = document.documentElement.scrollTop;
                        
                        var obj = document.getElementById(_moveId);
                        var top = obj.style.top;
                        var left = obj.style.left;
                        //obj.style.position = "absolute";
                        var xx = (movex + parseInt(left.substr(0, left.length - 2)));
                        var yy = (movey + parseInt(top.substr(0, top.length - 2)));
                        obj.style.left = xx + "px";
                        obj.style.top = yy + "px";
                    }
                };
                var mouseup = function(){
                    this.mousedownflag = 0;
                    //alert(obj.style.width);
                };
                var mouseout = function(){
                    this.mousedownflag = 0;
                };
                
                /**
                 *����¼�Դ����
                 *@param e �¼�����
                 */
                var getTarget = function(e){
                    return e.target || e.srcElement;
                };
                
				/**
				 *��鷢���¼�Դ��λ���Ƿ��ڹ涨��Χ��
				 */
                var checkTarget = function(e){
                    if (getTarget(e).id != _moveId) 
                        return false;
                    
                    //��¼������λ��
                    this.x = e.offsetX ? e.offsetX : e.layerX;
                    this.y = e.offsetY ? e.offsetY : e.layerY;
                    // alert(_range.join(","));
					if(_range.length > 0){
                        if (this.x < _range[0][0] || this.x > _range[0][1] || this.y < _range[1][0] || this.y > _range[1][1])//�жϷ����¼���λ���Ƿ��ڹ涨�ķ�Χ�ڲ� 
                            return false;
					}
                    
                    return true;
                };
                
                /**
                 * ����������õĺ����������϶��¼�
                 */
                var handler = function(){
                    //   alert("dadsdsadsadsad");
                    //  alert(id);
                    
                    var obj = document.getElementById(_moveId);
                    obj.onmousedown = function(event){
                        event = event || window.event;
                        //alert(!!checkTarget(event));
                        if (!!checkTarget(event)) 
                            mousedown(event);
                    };
                    obj.onmousemove = function(event){
                        event = event || window.event;
                        mousemove(event)
                    };
                    obj.onmouseup = function(){
                        mouseup()
                    };
                    obj.onmouseout = function(event){
                        mouseout()
                    };
                    if (_scrollflag) {
                        /**
                         * ����IE��FireFox��onscroll�����¼�
                         */
                        window.onscroll = function(){
                            HXL.scrollEvent.fixedFloatDiv(_moveId);
                        };
                    };
                    
                    return this;
                };
                return handler;
            };
            /**
             * �����϶��¼�����,��װ���϶����̷������¼��Ĵ�����
             * @param {Object} e
             */
            HXL.scrollEvent = {
                preScrollX: 0,
                preScrollY: 0,
                fixedFloatDiv: function(_moveId){
                    // alert('dadsa');
                    var obj = document.getElementById(_moveId);
                    var scrollX = 0;
                    var scrollY = 0;
                    // ��������֧�� pageYOffset, ͨ�� pageXOffset �� pageYOffset ��ȡҳ����Ӵ�֮��ľ��� ,chrome�����ַ�ʽ
                    if (typeof window.pageYOffset != 'undefined') {
                        scrollX = window.pageXOffset;
                        scrollY = window.pageYOffset;
                    }
                    // ��������֧�� compatMode, ����ָ���� DOCTYPE, ͨ�� documentElement ��ȡ����������Ϊҳ����Ӵ���ľ���
                    // IE ��, ��ҳ��ָ�� DOCTYPE, compatMode ��ֵ�� CSS1Compat, ���� compatMode ��ֵ�� BackCompat
                    else {
                        if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                            scrollX = document.documentElement.scrollLeft;
                            scrollY = document.documentElement.scrollTop;
                        }
                        // ��������֧�� document.body, ����ͨ�� document.body ����ȡ�����߶�
                        else {
                            if (typeof document.body != 'undefined') {
                                scrollX = document.body.scrollLeft;
                                scrollY = document.body.scrollTop;
                            }
                        }
                    }
                    var top = obj.style.top;
                    var left = obj.style.left;
                    //	alert(parseInt(top.substr(0, top.length-2)));
                    obj.style.top = (parseInt(top.substr(0, top.length - 2)) + scrollY - this.preScrollY) + "px";//��ǰdiv����������top�ľ���+��������������ƶ��ľ���=��ǰ����������ľ���-�ϴ�����������ľ��룩
                    obj.style.left = (parseInt(left.substr(0, left.length - 2)) + scrollX - this.preScrollX) + "px";
                    this.preScrollY = scrollY;//������������������y�᳤��
                    this.preScrollX = scrollX;//������������������x�᳤��
                }
            };
        })();