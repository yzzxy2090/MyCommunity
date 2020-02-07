package com.zxy.forum.service;

import com.zxy.forum.dto.QuestionDTO;
import com.zxy.forum.mapper.QuestionMapper;
import com.zxy.forum.mapper.UserMapper;
import com.zxy.forum.model.Question;
import com.zxy.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们希望在index中展示问题列表
 * 所以在indexController中要往前端传Question的相关信息
 * 但Model.Question中只有一个creator属性，这个creator没有和User关联起来
 * 我们希望传的creator和userMapper中的信息组装起来
 * 当一个请求需要组装User和Question时，需要一个中间层去做这件事
 * 这时就通过service层的QuestionService来组装
 */

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 遍历数据库中Question对象
     * 把它们的属性复制给新建的QuestionDTO对象
     * 在UserMapper中查questionMapper中creator对应的那个User
     * 然后将每个QuestionDTO对象的User属性设为Question的creator所对应的的User对象
     */
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
