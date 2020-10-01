package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.News


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList

object NewsRepository {

    val news: News
        get() {

            val newsArrayList =
                Gson().fromJson<ArrayList<News>>(json, object : TypeToken<ArrayList<News>>() {

                }.type)
            return newsArrayList[0]

        }

    val newsList: ArrayList<News>
        get() = Gson().fromJson<ArrayList<News>>(json, object : TypeToken<ArrayList<News>>() {

        }.type)

    internal var json = "[\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Tesla board says Musk started going-private talks last week\",\n" +
            "    \"desc\"  : \"Twitter and most of the rest of the internet freaked out over news that Elon Musk wanted to take Tesla private at $420 per share. Today, we received some confirmation that it wasn't a spur-of-the-moment thing.Several members of Tesla's board issued a joint statement on the company's investor-relations site. The statement says that Elon started discussing taking the company private with the board last week. The talks covered both the reasons for doing so -- which Musk also laid out in an email to employees that was subsequently posted online -- as well as the funding. That last part is important, because Tesla's going to need a lot of it.\",\n" +
            "    \"date\"  : \"Aug. 5, 2018\",\n" +
            "    \"category\" : \"Technology\",\n" +
            "    \"ago\"   : \"Yesterday\",\n" +
            "    \"news_image\" : \"news1\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"20.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Blockchain Decoded Carbon credits are a common mechanism\",\n" +
            "    \"desc\"  : \"Carbon credits are a common mechanism used to help cut carbon dioxide emissions that fuel climate change, and now there's a blockchain technology project to try to improve the idea. Veridium Labs, with assistance from IBM, has launched a blockchain network designed to track how companies or other entities buy and sell carbon credits. Carbon credit systems cap allowed carbon dioxide releases but let companies that don't reach the cap sell credits to those who go over. Carbon credit systems also let companies pay others that do things like plant forests to offset their carbon emissions. So why use blockchain for it? In a nutshell, blockchain technology builds trust into any kind of system that records transactions.\",\n" +
            "    \"date\"  : \"Aug. 6, 2018\",\n" +
            "    \"category\" : \"Technology\",\n" +
            "    \"ago\"   : \"1 week ago\",\n" +
            "    \"news_image\" : \"news2\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"32.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Fight the Power We feast on fossil fuels to power our cars\",\n" +
            "    \"desc\"  : \"We feast on fossil fuels to power our cars, trains, manufacturing plants and cities, but our reliance on them means we continue to flood our atmosphere with carbon dioxide now reaching levels higher than ever before. What if we could directly capture CO2 from the atmosphere and turn that into fuel? Previous research suggested the idea of sucking carbon out of the air  would prove too costly, with CO2 removal breaking the bank at $600 per ton. However, new research published in Joule on June 7 by Canadian company Carbon Engineering demonstrates that they can suck CO2 out of the air for between $94 and $232 per ton.\",\n" +
            "    \"date\"  : \"Aug. 6, 2018\",\n" +
            "    \"category\" : \"Technology\",\n" +
            "    \"ago\"   : \"2 week ago\",\n" +
            "    \"news_image\" : \"news3\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"2.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Batteries Not Included Tech has been part of our lives a lot longer than\",\n" +
            "    \"desc\"  : \"Tech has been part of our lives a lot longer than you might realize. That game of Tetris that hooked you as a kid? Tech. Ditto for mastering Killer Queen on Guitar Hero or seeing the ultrasound image of your baby. There's probably a tech connection to many of your favorite memories. Those are stories worth sharing, which is why the CNET team decided to talk about the experiences that remind us why this stuff is cool.\",\n" +
            "    \"date\"  : \"Aug. 16, 2018\",\n" +
            "    \"category\" : \"Technology\",\n" +
            "    \"ago\"   : \"3 week ago\",\n" +
            "    \"news_image\" : \"news4\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"2.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Follow the Money In CNET's series Follow the Money, we'll explore what a cashless\",\n" +
            "    \"desc\"  : \"In CNET's series Follow the Money, we'll explore what a cashless world might look like and the many ways digital cash will reshape our lives. We'll tell you about efforts using apps, services and new devices to give people who don't have bank accounts ways to more securely move their money around. Some of these concepts come with new risks, though, including one idea that uses volatile cryptocurrencies to offer more financial inclusion. \",\n" +
            "    \"date\"  : \"Aug. 19, 2018\",\n" +
            "    \"category\" : \"Business\",\n" +
            "    \"ago\"   : \"3 week ago\",\n" +
            "    \"news_image\" : \"news5\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"2.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Time to save the Great Barrier Reef from dying Two major bleaching events have wracked the Great Barrier Reef \",\n" +
            "    \"desc\"  : \"Two major bleaching events have wracked the Great Barrier Reef over the last two years, leaving chunks of it dead. Bleachings happen when the coral expels tiny algae, called zooxanthellae, that live inside it and provide its food and create its rainbow hues. Without zooxanthellae, the reef's tissue turns transparent and the coral starves. Global warming, fueled by our reliance on petroleum and coal, has pushed ocean temperatures 0.68 Celsius over the past century. That might not seem like much, but it's enough to prompt the coral and algae to abandon their symbiotic relationship. While coral doesn't die immediately, the bleachings have been intense enough to kill huge swaths of one of the world's most stunning natural wonders, including 29 percent of its shallow-water coral in 2016 alone.\",\n" +
            "    \"date\"  : \"Aug. 8, 2018\",\n" +
            "    \"category\" : \"Business\",\n" +
            "    \"ago\"   : \"3 week ago\",\n" +
            "    \"news_image\" : \"news6\",\n" +
            "    \"publisher\" : \"CNET\",\n" +
            "    \"publisher_image\" : \"cnet\",\n" +
            "    \"total_like\" : \"2.2 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Apple HomePod The fabric mesh-wrapped HomePod, available in either space gray\",\n" +
            "    \"desc\"  : \"The fabric mesh-wrapped HomePod, available in either space gray or white, weighs a hefty 5.5 pounds (2.5 kg). It's 6.8 inches tall and 5.6 inches wide (170 by 140 mm) -- petite compared to the 11.7-pound (5.3 kg) and foot-wide Google Home Max. But the HomePod feels surprisingly dense when you lift it, as I learned while carrying it through the airport. In true Apple form, the HomePod has a sleek, minimal design. It looks good, but not distractingly so, and I'm glad you get a couple color finishes to choose from. We got a white HomePod and while I like how it looks, I can easily imagine smudging it with repeat handling. The HomePod's mesh exterior isn't interchangeable like the Amazon Echo's removable shell. What you buy is what you get. You can, however, clean it with a dry or damp cloth.\",\n" +
            "    \"date\"  : \"Aug. 9, 2018\",\n" +
            "    \"category\" : \"Hot Products\",\n" +
            "    \"ago\"   : \"3 week ago\",\n" +
            "    \"news_image\" : \"news7\",\n" +
            "    \"publisher\" : \"BBC\",\n" +
            "    \"publisher_image\" : \"bbc\",\n" +
            "    \"total_like\" : \"2.3 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Google Home Mini You can buy the Google Home Mini now for $50 \",\n" +
            "    \"desc\"  : \"You can buy the Google Home Mini now for $50 (though it's often on sale for even less) via the Google store and through a number of electronics outlets such as Best Buy. It's available overseas as well. The UK price is £50, and you can buy it in Australia for AU$80.Google kept things simple with the Mini's design. It's an oblong orb of plastic and fabric with no visible buttons, save for a slider to mute the microphone that's hidden in the back. It comes in your choice of three colors -- chalk (light gray), charcoal (dark gray), or coral (pinkish orange).To wake it up, you say OK, Google or Hey, Google, and then you give it a question or command. You can ask it to play music, turn your smart home gadgets on and off, look up a fact for you, control Netflix and YouTube on your Chromecast-enabled TV plus a whole host of other tricks. It puts the power of the internet just an utterance away, with the Google Assistant as your concierge.\",\n" +
            "    \"date\"  : \"Aug. 9, 2018\",\n" +
            "    \"category\" : \"Hot Products\",\n" +
            "    \"ago\"   : \"1 month ago\",\n" +
            "    \"news_image\" : \"news8\",\n" +
            "    \"publisher\" : \"CNN\",\n" +
            "    \"publisher_image\" : \"cnn\",\n" +
            "    \"total_like\" : \"2.3 k\"\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "\n" +
            "    \"title\" : \"Samsung Galaxy S9 Samsung will introduce the Galaxy Note 9, its premium\",\n" +
            "    \"desc\"  : \"Samsung will introduce the Galaxy Note 9, its premium large-screen smartphone sporting an S Pen stylus. The stylus could include improvements that help it move beyond writing, drawing and navigation, and the uber-popular Fortnite game could make its Android debut on the Note 9. But overall, the new model is expected to get tweaks instead of major changes, something that could push the Note to the same fate as the Galaxy S9 and S9 Plus: sluggish sales. Samsung's mainstream phones, which hit the market on March 16, added better cameras and improved audio, but they didn't quite fly off the shelves. In the second quarter -- the first full period of Galaxy S9 sales -- Samsung actually lost more market share than any other major handset maker and posted its worst performance since the second quarter of 2013. Its shipments tumbled 11 percent from the previous year to 70.8 million units, according to IHS Markit.\",\n" +
            "    \"date\"  : \"Aug. 9, 2018\",\n" +
            "    \"category\" : \"Hot Products\",\n" +
            "    \"ago\"   : \"1 month ago\",\n" +
            "    \"news_image\" : \"news9\",\n" +
            "    \"publisher\" : \"BBC\",\n" +
            "    \"publisher_image\" : \"bbc\",\n" +
            "    \"total_like\" : \"2.9 k\"\n" +
            "  }\n" +
            "\n" +
            "\n" +
            "]"

}
