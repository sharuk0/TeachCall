package dbp.techcall.post.domain;

import dbp.techcall.post.dto.PostInfo;
import dbp.techcall.post.dto.PostInfoResponse;
import dbp.techcall.post.infrastructure.PostRepository;
import dbp.techcall.professor.domain.Professor;
import dbp.techcall.professor.dto.ProfessorNames;
import dbp.techcall.professor.infrastructure.ProfessorRepository;
import dbp.techcall.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Page<PostInfoResponse> getAllPostWithPagination(Pageable page, Student student) {
        Page<Post> posts = postRepository.findAll(page);

        return posts.map(post -> {
            PostInfoResponse postInfoResponse = new PostInfoResponse();

            ProfessorNames professorNames = professorRepository.findProfessorNamesById(post.getProfessor().getId());

            postInfoResponse.setFirstName(professorNames.getFirstName());
            postInfoResponse.setLastName(professorNames.getLastName());
            postInfoResponse.setId(post.getId());
            postInfoResponse.setTitle(post.getTitle());
            postInfoResponse.setBody(post.getBody());
            postInfoResponse.setCreatedAt(post.getCreatedAt());
            postInfoResponse.setLiked(student.getLikedPosts().contains(post));
            postInfoResponse.setLikesQ(post.getLikes().size());
            return postInfoResponse;
        });
    }

    public Page<PostInfo> getCurrentUserPost(Professor professor) {
        Pageable page = PageRequest.of(0, 10);
        return postRepository.getCurrentUserPostWithPagination(professor.getId(), page);
    }
}
