# demo-spring-boot
#   spring statemachine官方文档阅读

##  特性
1. 使状态机模式更加易用
2. 分层架构更容易使用复杂的状态配置
3. 支持regions提供更复杂的状态配置
4. 支持`triggers`, `transitions`, `guards` 和 `actions`.
5. 类型安全的配置适配器
6. 支持事件监听
7. 整合spring IOC

## 使用场景
1. 应用或者部分结构可以以状态呈现
2. 分离复杂的逻辑成为小的可管理的任务
3. 存在并发和异步的问题

##  hello world
1.  枚举状态和事件
    ```
    public enum Events {
        EVENT1, EVENT2
    }
    public enum States {
        STATE1, STATE2
    }
    ```
1.  创建Statemachine
    ```
    @Configuration
    @EnableStateMachine
    class StatemachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

        @Override
        public void configure(StateMachineStateConfigurer<States, Events> states)
                throws Exception {
            states
                    .withStates()
                    .initial(States.STATE1)
                    .states(EnumSet.allOf(States.class));
        }
    
        @Override
        public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source(States.STATE1).target(States.STATE2)
                    .event(Events.EVENT1)
                    .and()
                    .withExternal()
                    .source(States.STATE2).target(States.STATE1)
                    .event(Events.EVENT2);
        }
    }
    ```
2.  创建model
    ```
    @WithStateMachine
    @Slf4j
    public class Mybean {
        @OnTransition(target = "STATE1")
        public void toState1() {
            log.info("to state1");
        }
    
        @OnTransition(target = "STATE2")
        public void toState2() {
            log.info("to state2");
        }
    }    
    ```
3.  创建服务
    ```
    @Service
    public class StatemachineService {
        @Autowired
        StateMachine<States, Events> stateMachine;
    
        void doSignals() {
            stateMachine.start();
            stateMachine.sendEvent(Events.EVENT1);
            stateMachine.sendEvent(Events.EVENT2);
        }
    }
    ```
    
##  配置
1.  配置 `annotations`
    1.1 `@EnableStateMachine`
        *   创建StateMachine所必须的annotation
        *   通常使用`@Configuration`并继承`EnumStateMachineConfigurerAdapter` 或 `StateMachineConfigurerAdapter`实现回调
    1.2 `@EnableStateMachineFactory`
        *   创建`StateMachineFactory`时使用

2.  配置 `states`
    2.1 常规配置
    ```
    states
        .withStates()
            .initial(States.S1)
            .end(States.SF)
            .states(EnumSet.allOf(States.class));    
    ```
    2.2 `StateMachineConfigurerAdapter`
        *   实现了 `StateMachineConfigurer`
    2.3 `EnumStateMachineConfigurerAdapter`
            *   `限定回调为枚举`
            *   继承自 `StateMachineConfigurerAdapter`
3.  配置 `Hierarchical States`
    *   分层状态配置
    ```
    states
        .withStates()
            .initial(States.S1)
            .state(States.S1)
            .and()
            .withStates()
                .parent(States.S1)
                .initial(States.S2)
                .state(States.S2);    
    ```
4.  配置 `Regions`
    *   正交状态,无需特殊配置
    *   当分层状态有交集的时候会创建正交状态
    *   如下示例的状态
    ```
    states
        .withStates()
            .initial(States2.S1)
            .state(States2.S2)
            .and()
            .withStates()
                .parent(States2.S2)
                .initial(States2.S2I)
                .state(States2.S21)
                .end(States2.S2F)
                .and()
            .withStates()
                .parent(States2.S2)
                .initial(States2.S3I)
                .state(States2.S31)
                .end(States2.S3F);
    ```
5.  配置 `Transitions`
    *   state machine支持不同类型的状态转换,包括 `external`, `internal` 和 `local`
    *   转换状态可以被 发送到状态机的事件 和 定时器触发
    *   状态转换示例
    ```
    transitions
        .withExternal()
            .source(States.S1).target(States.S2)
            .event(Events.E1)
            .and()
        .withInternal()
            .source(States.S2)
            .event(Events.E2)
            .and()
        .withLocal()
            .source(States.S2).target(States.S3)
            .event(Events.E3);    
    ```
6.  配置 `Guards`
    *   `Guards` 用来保护 状态转换
    *   `Guard` 接口用来判定是否有进入 `StateContext` 的权限
    *   支持两种类型的Guard 一种是创建`Guard Bean` 一种是 使用 `SpelExpressionGuard`
    *   保护器示例
    ```
    transitions
        .withExternal()
            .source(States.S1).target(States.S2)
            .event(Events.E1)
            .guard(guard())
            .and()
        .withExternal()
            .source(States.S2).target(States.S3)
            .event(Events.E2)
            .guardExpression("true");    
    --------------------------------------------------        
    Bean
        public Guard<States, Events> guard() {
            return new Guard<States, Events>() {
    
                @Override
                public boolean evaluate(StateContext<States, Events> context) {
                    return true;
                }
            };
        }
    ```
7.  配置 `Actions`
    *   Action 可以定义为执行转换和状态本身
    *   Action 作为被触发的转换的结果被执行
    *   Actions示例
    ```
    transitions
        .withExternal()
            .source(States.S1)
            .target(States.S2)
            .event(Events.E1)
            .action(action());
    
    -----------------------------------
    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                // do something
            }
        };
    }    
    ```
8.  配置 `State Actions`
    8.1 Transition Action Error Handling
        *  错误处理 用户可以使用自定义异常,环境变量使用`StateContext`
        *   示例
        
        ```
            transitions
                .withExternal()
                    .source(States.S1)
                    .target(States.S2)
                    .event(Events.E1)
                    .action(action(), errorAction());
            
        --------------------------------
            @Bean
            public Action<States, Events> action() {
                return new Action<States, Events>() {
        
                    @Override
                    public void execute(StateContext<States, Events> context) {
                        throw new RuntimeException("MyError");
                    }
                };
            }
        
        ---------------------------------
            @Bean
            public Action<States, Events> errorAction() {
                return new Action<States, Events>() {
        
                    @Override
                    public void execute(StateContext<States, Events> context) {
                        // RuntimeException("MyError") added to context
                        Exception exception = context.getException();
                        exception.getMessage();
                    }
                };
            }

        ```

    8.2 State Action Error Handling
    
        *   错误处理 通过状态的起始和结束状态以及状态的行为进行处理
        *   示例
        ```
        states
        .withStates()
            .initial(States.S1)
            .stateEntry(States.S2, action(), errorAction())
            .stateDo(States.S2, action(), errorAction())
            .stateExit(States.S2, action(), errorAction())
            .state(States.S3);
        
    
        @Bean
        public Action<States, Events> action() {
            return new Action<States, Events>() {
    
                @Override
                public void execute(StateContext<States, Events> context) {
                    throw new RuntimeException("MyError");
                }
            };
        }
    
        @Bean
        public Action<States, Events> errorAction() {
            return new Action<States, Events>() {
    
                @Override
                public void execute(StateContext<States, Events> context) {
                    // RuntimeException("MyError") added to context
                    Exception exception = context.getException();
                    exception.getMessage();
                }
            };
        }
        ```
9.  配置 `Pseudo States`
    9.1 Initial State
        *   通过 `initial()` 方法标记初始化状态
        *   示例
        
        ```
        states
            .withStates()
                .initial(States.S1, initialAction())
                .end(States.SF)
                .states(EnumSet.allOf(States.class));
        ---------------------------    
            @Bean
            public Action<States, Events> initialAction() {
                return new Action<States, Events>() {
        
                    @Override
                    public void execute(StateContext<States, Events> context) {
                        // do something initially
                    }
                };
            }
        ```
    9.2 Terminate State
        *   通过 `end()` 方法标记最终状态
        *   每个独立的 `sub-machine` 或 `region`最多可以调用一次
        *   示例
        
        ```
        states
            .withStates()
                .initial(States.S1)
                .end(States.SF)
                .states(EnumSet.allOf(States.class));
        ```
    9.3 History State
    9.4 Choice State
    9.5 Junction State
    9.6 Fork State
    9.7 Join State
    9.8 Exit/Entry Point States
10. 配置 `Common Settings`
11. 配置 `Model`
12. 注意事项

##  State Machine Factories
*   当事件被触发后,如果转换后的状态不是理想的状态,这个状态的处理就需要被推迟,state machine支持这种延迟状态的处理
*   示例
    ```
    states
        .withStates()
            .initial("READY")
            .state("DEPLOYPREPARE", "DEPLOY")
            .state("DEPLOYEXECUTE", "DEPLOY");
    }
    -------------------------------------------
    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source("READY").target("DEPLOYPREPARE")
                .event("DEPLOY")
                .and()
            .withExternal()
                .source("DEPLOYPREPARE").target("DEPLOYEXECUTE")
                .and()
            .withExternal()
                .source("DEPLOYEXECUTE").target("READY");
    }
    ```

##  概念整理
### Deferred Events

### copes

### Actions

### Using Guards

### Using Extended State

##  Triggering Transitions

##  Listening State Machine Events

##  Context Integration

##  Accessor

##  Interceptor

##  Security

##  Error Handling

##  Persisting State Machine

##  Spring Boot Support

##  other

###  State Machine ID
*   machine ID仅仅用来标记一个特定的 state machine
