//package com.junit.demo.graphql.kickstar.resolver;
//
//import com.coxautodev.graphql.tools.GraphQLResolver;
//import com.junit.demo.graphql.kickstar.entity.Speaker;
//import com.junit.demo.graphql.kickstar.entity.Talk;
//import com.junit.demo.graphql.kickstar.service.TalkService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Component
//public class SpeakerResolver implements GraphQLResolver<Speaker> {
//
//    @Resource
//    private TalkService talkService;
//
//    public List<Talk> talks(Speaker speaker) {
//        return talkService.findAllTalksBySpeaker(speaker);
//    }
//
//}
