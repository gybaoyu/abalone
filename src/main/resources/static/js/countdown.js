var WINDOW_WIDTH = 1024;
var WINDOW_HEIGHT = 768;
var RADIUS = 8;
var MARGIN_TOP = 60;
var MARGIN_LEFT = 30;

//var endTime = new Date();
//endTime.setTime( endTime.getTime() + 3600*1000 )
var curShowTimeSeconds = 0

var balls = [];//用来存放运动的小球的数组
const colors = ["#33B5E5","#0099CC","#AA66CC","#9933CC","#99CC00","#669900","#FFBB33","#FF8800","#FF4444","#CC0000"]

window.onload = function(){

    WINDOW_WIDTH = document.body.clientWidth;
    WINDOW_HEIGHT = document.body.clientHeight;

    MARGIN_LEFT = Math.round(WINDOW_WIDTH /10);//十分之一的左边距
    RADIUS = Math.round(WINDOW_WIDTH * 4 / 5 / 108)-1;//所有文字所占的内容是4/5,然后平均分为108个（半径+1）

    MARGIN_TOP = Math.round(WINDOW_HEIGHT /5);//五分之一的上边距

    var canvas = document.getElementById('canvas');
    var context = canvas.getContext("2d");

    canvas.width = WINDOW_WIDTH;//画布铺满整个屏幕
    canvas.height = WINDOW_HEIGHT;

    curShowTimeSeconds = getCurrentShowTimeSeconds();//设定当前显示时间
    setInterval(
        function(){
            render( context );//对画布的渲染
            update();//对显示数据的更新
        },50
    );
}

//获得当前时间 返回秒
function getCurrentShowTimeSeconds() {
    var curTime = new Date();
    var ret = curTime.getHours() * 3600 + curTime.getMinutes() * 60 + curTime.getSeconds();
    return ret;
}

function update(){

    var nextShowTimeSeconds = getCurrentShowTimeSeconds();

    var nextHours = parseInt( nextShowTimeSeconds / 3600);
    var nextMinutes = parseInt( (nextShowTimeSeconds - nextHours * 3600)/60 );
    var nextSeconds = nextShowTimeSeconds % 60;

    var curHours = parseInt( curShowTimeSeconds / 3600);
    var curMinutes = parseInt( (curShowTimeSeconds - curHours * 3600)/60 );
    var curSeconds = curShowTimeSeconds % 60;

    if( nextSeconds != curSeconds ){//此时nextSeconds代表新的时间，curSeconds代表旧的时间
    	//分别对每一位上的数字进行判断，如果有变化的话就给每个要变化的小球添加初始属性，重力加速度等
        if( parseInt(curHours/10) != parseInt(nextHours/10) ){
            addBalls( MARGIN_LEFT + 0 , MARGIN_TOP , parseInt(curHours/10) );
        }
        if( parseInt(curHours%10) != parseInt(nextHours%10) ){
            addBalls( MARGIN_LEFT + 15*(RADIUS+1) , MARGIN_TOP , parseInt(curHours/10) );
        }

        if( parseInt(curMinutes/10) != parseInt(nextMinutes/10) ){
            addBalls( MARGIN_LEFT + 39*(RADIUS+1) , MARGIN_TOP , parseInt(curMinutes/10) );
        }
        if( parseInt(curMinutes%10) != parseInt(nextMinutes%10) ){
            addBalls( MARGIN_LEFT + 54*(RADIUS+1) , MARGIN_TOP , parseInt(curMinutes%10) );
        }

        if( parseInt(curSeconds/10) != parseInt(nextSeconds/10) ){
            addBalls( MARGIN_LEFT + 78*(RADIUS+1) , MARGIN_TOP , parseInt(curSeconds/10) );
        }
        if( parseInt(curSeconds%10) != parseInt(nextSeconds%10) ){
            addBalls( MARGIN_LEFT + 93*(RADIUS+1) , MARGIN_TOP , parseInt(nextSeconds%10) );
        }

        curShowTimeSeconds = nextShowTimeSeconds;//跟新时间
    }

    updateBalls();//让已经有运动属性的小球动起来

    console.log( balls.length);
}

//让已经有运动属性的小球动起来
function updateBalls(){

    for( var i = 0 ; i < balls.length ; i ++ ){

        balls[i].x += balls[i].vx;//运动小球x轴坐标

	    var c = 1.0;//表示时间，用来计算落地时的速度
		if( balls[i].y + RADIUS + balls[i].vy >= WINDOW_HEIGHT ){
		    c = ( WINDOW_HEIGHT - (balls[i].y+ RADIUS) ) / balls[i].vy;//c表示最后快落地的不到一秒的时间
//		    console.log( c );
		}
	        
		balls[i].y += balls[i].vy;//运动小球y轴坐标
		balls[i].vy += c * balls[i].g;//运动小球y轴速度

        if( balls[i].y >= WINDOW_HEIGHT-RADIUS ){
            balls[i].y = WINDOW_HEIGHT-RADIUS;
	    	balls[i].vy = - Math.abs(balls[i].vy)*0.75;//小球反弹时损失了一部分动能，所以速度减少
        }
    }

	for(var i = 0 ; i < balls.length ; i++){//将掉出的小球给去除掉
		if( balls[i].x + RADIUS < 0 || balls[i].x -RADIUS > WINDOW_WIDTH )
			balls.splice(i,1);
	}
	
}

//形成运动的小球
function addBalls( x , y , num ){//传过来的num为旧的要变化的显示数字

    for( var i = 0  ; i < digit[num].length ; i ++ )
        for( var j = 0  ; j < digit[num][i].length ; j ++ )
            if( digit[num][i][j] == 1 ){
                var aBall = {//给将要弹出去的小球添加初始属性
                    x:x+j*2*(RADIUS+1)+(RADIUS+1),
                    y:y+i*2*(RADIUS+1)+(RADIUS+1),
                    g:1.5+Math.random(),//重力加速度，向下为正方向
                    vx:Math.pow( -1 , Math.ceil( Math.random()*1000 ) ) * 4,//x轴初始速度
                    vy:-5,//y轴初始速度
                    color: colors[ Math.floor( Math.random()*colors.length ) ]//颜色随机
                }
                balls.push( aBall );//小球入栈
            }
}

//根据当前时间画出小球时间
function render( cxt ){

    cxt.clearRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);//清屏

    var hours = parseInt( curShowTimeSeconds / 3600);//时
    var minutes = parseInt( (curShowTimeSeconds - hours * 3600)/60 );//分
    var seconds = curShowTimeSeconds % 60;//秒

    renderDigit( MARGIN_LEFT , MARGIN_TOP , parseInt(hours/10) , cxt );
    renderDigit( MARGIN_LEFT + 15*(RADIUS+1) , MARGIN_TOP , parseInt(hours%10) , cxt );
    renderDigit( MARGIN_LEFT + 30*(RADIUS+1) , MARGIN_TOP , 10 , cxt);//冒号
    renderDigit( MARGIN_LEFT + 39*(RADIUS+1) , MARGIN_TOP , parseInt(minutes/10) , cxt);
    renderDigit( MARGIN_LEFT + 54*(RADIUS+1) , MARGIN_TOP , parseInt(minutes%10) , cxt);
    renderDigit( MARGIN_LEFT + 69*(RADIUS+1) , MARGIN_TOP , 10 , cxt);//冒号
    renderDigit( MARGIN_LEFT + 78*(RADIUS+1) , MARGIN_TOP , parseInt(seconds/10) , cxt);
    renderDigit( MARGIN_LEFT + 93*(RADIUS+1) , MARGIN_TOP , parseInt(seconds%10) , cxt);

    for( var i = 0 ; i < balls.length ; i ++ ){
        cxt.fillStyle=balls[i].color;

        cxt.beginPath();
        cxt.arc( balls[i].x , balls[i].y , RADIUS , 0 , 2*Math.PI , true );
        cxt.closePath();

        cxt.fill();
    }
}

//根据矩阵画出小球
function renderDigit( x , y , num , cxt ){

    cxt.fillStyle = "rgb(0,102,153)";

    for( var i = 0 ; i < digit[num].length ; i ++ )
        for(var j = 0 ; j < digit[num][i].length ; j ++ )
            if( digit[num][i][j] == 1 ){
                cxt.beginPath();
                cxt.arc( x+j*2*(RADIUS+1)+(RADIUS+1) , y+i*2*(RADIUS+1)+(RADIUS+1) , RADIUS , 0 , 2*Math.PI );//小球间间距是2px
                cxt.closePath();

                cxt.fill();
            }
}