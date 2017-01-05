package org.handler.property.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * 注册普通WebScoket
 * @author PengBin
 * @date 2016年6月21日 下午5:29:00
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	WebSocketConfig(){
		System.out.println("注册普通WebScoket");
	}
    @Autowired
    @Lazy
    private SimpMessagingTemplate template;

    /** {@inheritDoc} */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	 registry.addHandler(logWebSocketHandler(), "/log");
      //  registry.addHandler(logWebSocketHandler(), "/log/sockjs").withSockJS(); // 此处与客户端的 URL 相对应
    }

    @Bean
    public WebSocketHandler logWebSocketHandler() {
        return new LogWebSocketHandler(template);
    }

}
