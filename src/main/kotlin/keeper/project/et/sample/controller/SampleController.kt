package keeper.project.et.sample.controller

import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import keeper.project.et.sample.service.SampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("/sample")
class SampleController {

    @Autowired
    lateinit var sampleService: SampleService

    @GetMapping("/get")
    fun getSample(@RequestParam("id") id: String, @RequestParam("pw") pw: String) {
        sampleService.getLogin(id, pw)
    }

    @PostMapping("/post")
    fun postSample(@RequestBody sampleSignUpDTO: SampleSignUpDTO) {
        sampleService.postSignUp(sampleSignUpDTO)
    }

    @PutMapping("/put")
    fun putSample(@RequestBody sampleChangePwDTO: SampleChangePwDTO) {
        sampleService.putChangePw(sampleChangePwDTO)
    }

    @DeleteMapping("/delete")
    fun deleteSample(@RequestParam("id") id:String) {
        sampleService.deleteUser(id)
    }
}
