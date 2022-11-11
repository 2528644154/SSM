package com.powernode.common;

/*定义工程中公共使用的常量*/
public class Constants {

    //想在不同的控制层中调用相同的属性的值，那么就可以把这个属性定义成static final静态常量，然后拿到的值就是一样的了

    //标记登录
    /*static final经过它修饰的属性表示一旦给了值，就不可在修改，并且可以通过类名来访问*/
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";

    /*登录人id*/
    /*用户登录的id:用来标识session对象*/
    public static final String USER_SESSION_ID = "USER_SESSION_ID";

    //标记角色
    public static final String ROLE_SESSION_KEY = "ROLE_KEY";

    //标记验证码
    public static final String CODE_SESSION_KEY = "CODE_SESSION_KEY";

    //标记头像
    public static final String DEFAULT_HEAD_IMG = "default.png";

}
