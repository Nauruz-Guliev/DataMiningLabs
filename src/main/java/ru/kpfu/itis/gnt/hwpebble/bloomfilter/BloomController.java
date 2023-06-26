package ru.kpfu.itis.gnt.hwpebble.bloomfilter;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class BloomController {

    private final BloomFilter bloomFilter;


    private static final String BLOOM_ITEM_KEY = "bloom";

    @GetMapping("bloom")
    public String newBloom(ModelMap map) {
        map.put(BLOOM_ITEM_KEY, new Bloom());
        map.put("originalText", TextHolder.text);
        return "bloom";
    }


    @PostMapping("bloom")
    public String newBloomCheck(
            RedirectAttributes redirectAttributes,
            @ModelAttribute(BLOOM_ITEM_KEY) Bloom bloom
    ) {
        redirectAttributes.addFlashAttribute("word", bloom.getWord());
        redirectAttributes.addFlashAttribute("message", bloomFilter.mightContain(bloom.getWord()));
        redirectAttributes.addFlashAttribute("size", bloomFilter.getBitSetSize());
        redirectAttributes.addFlashAttribute("amount", bloomFilter.getNumHashFunctions());
        redirectAttributes.addFlashAttribute("count", bloomFilter.getAmountOfWords());
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("BC#newBloom").build();
    }
}
