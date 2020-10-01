package com.example.kotlindemoapp.Helper

import com.example.kotlindemoapp.Model.GeneralList


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList


object GeneralListRepository {
    val generalList: ArrayList<GeneralList>
        get() = Gson().fromJson<ArrayList<GeneralList>>(
            json,
            object : TypeToken<ArrayList<GeneralList>>() {

            }.type
        )

    internal var json = "[\n" +
            "  {\n" +
            "    \"name\":\"Lorem ipsum\",\n" +
            "    \"caption\":\"Aenean\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget elementum dui, at accumsan nunc. Vestibulum et velit bibendum, tempor massa tempus, commodo neque. Nunc auctor dignissim odio. Integer auctor, urna a tristique pharetra, justo massa tincidunt dolor, eu congue enim neque blandit lorem. Etiam malesuada, lacus a ullamcorper venenatis, eros est finibus justo, eget dapibus lorem nulla a dui. Vivamus fermentum orci ac congue ultrices.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Pellentesque nec\",\n" +
            "    \"caption\":\"Quisque\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Pellentesque aliquam magna at lobortis condimentum. In tempor dignissim sodales. Maecenas a est sit amet justo tincidunt lacinia id nec est. Aliquam luctus vehicula arcu, at lobortis libero mattis at. Aliquam scelerisque dolor eget sapien posuere tincidunt. In iaculis rutrum lectus. In ultricies nisi orci, sed auctor nisi lacinia a. Ut eu bibendum mauris. Mauris ultrices magna vel sapien tempor efficitur. Fusce tristique condimentum condimentum.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Duis id mauris\",\n" +
            "    \"caption\":\"Pellentesque\",\n" +
            "    \"image\":\"banner\",\n" +
            "    \"detail\":\"Aenean sed leo non magna efficitur dictum. Aenean vel turpis bibendum diam cursus bibendum. Quisque rhoncus urna nec nisl pharetra, ut posuere quam mollis. Mauris eget luctus massa. Integer et magna ut sem dictum semper sit amet scelerisque felis. Integer varius, lorem quis blandit venenatis, nibh quam egestas nisi, eget vulputate quam quam id urna. Sed id felis sed nisi scelerisque malesuada. Praesent ac eros auctor, lobortis dui in, pretium mauris. \"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Aenean tincidunt\",\n" +
            "    \"caption\":\"Donec\",\n" +
            "    \"image\":\"banner\",\n" +
            "    \"detail\":\"Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam ac vestibulum nulla. Fusce vel elementum quam, euismod sollicitudin ipsum. In commodo aliquet augue at lobortis. Praesent blandit metus euismod laoreet tempus. Proin interdum risus tellus, vitae rhoncus leo commodo non. Praesent semper tellus ultrices vulputate varius. Morbi tincidunt ipsum eget quam interdum, et aliquam leo commodo.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Pellentesque eu\",\n" +
            "    \"caption\":\"Morbi\",\n" +
            "    \"image\":\"banner\",\n" +
            "    \"detail\":\"Pellentesque sed sem luctus, efficitur elit ut, hendrerit leo. Sed tincidunt sit amet enim non tristique. Praesent rutrum tellus urna, quis viverra nisl congue nec. \"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Nunc sed nulla\",\n" +
            "    \"caption\":\"Proin\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Curabitur tristique justo ut sapien cursus fermentum. Duis quis augue eros. Donec rhoncus ornare orci, a faucibus arcu lacinia tristique. Integer sem sem, rhoncus ut dictum at, sodales et ex. Maecenas ac enim vel mi varius tempor. Suspendisse sagittis sodales facilisis.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"In convallis mi\",\n" +
            "    \"caption\":\"Pellentesque\",\n" +
            "    \"image\":\"cafe2\",\n" +
            "    \"detail\":\"Nunc sit amet vulputate erat. Curabitur ac turpis dolor. Duis tristique accumsan posuere. Morbi quam metus, condimentum et magna et, bibendum facilisis neque.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Vestibulum ac est\",\n" +
            "    \"caption\":\"Donec\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Fusce eu ex elementum, euismod nunc vel, lobortis neque. Integer imperdiet tortor suscipit egestas cursus. Praesent eleifend vestibulum nibh, a mollis dui hendrerit eu.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Pellentesque pretium\",\n" +
            "    \"caption\":\"Vivamus\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Duis hendrerit massa nec eros elementum, eget vestibulum arcu scelerisque. Vestibulum tincidunt interdum sapien nec sagittis. Sed eu vestibulum erat. Sed vitae arcu quis enim rhoncus placerat in eu lacus. Sed non mollis magna, a venenatis nisi. Nunc commodo odio ut pellentesque ultrices. Phasellus aliquet convallis est, nec tempus leo lacinia vel.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Nunc aliquet purus\",\n" +
            "    \"caption\":\"Dignissim\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Donec facilisis tortor et dolor hendrerit, ac iaculis ipsum congue. Sed bibendum quam ut massa efficitur vestibulum. Nam mollis posuere odio, a condimentum mauris suscipit quis. Sed dolor orci, tristique sed lectus sit amet, elementum auctor tellus. Vestibulum mollis eu quam et dignissim. Nulla volutpat sagittis justo, ut laoreet metus. Nunc congue dui non auctor convallis.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Praesent scelerisque\",\n" +
            "    \"caption\":\"Finibus\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Mauris eget luctus massa. Integer et magna ut sem dictum semper sit amet scelerisque felis. Integer varius, lorem quis blandit venenatis, nibh quam egestas nisi, eget vulputate quam quam id urna. Sed id felis sed nisi scelerisque malesuada. Praesent ac eros auctor, lobortis dui in, pretium mauris.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Aliquam consectetur\",\n" +
            "    \"caption\":\"Porttitor\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"detail\":\"Sed vitae arcu quis enim rhoncus placerat in eu lacus. Sed non mollis magna, a venenatis nisi. Nunc commodo odio ut pellentesque ultrices. Phasellus aliquet convallis est, nec tempus leo lacinia vel. Donec facilisis tortor et dolor hendrerit, ac iaculis ipsum congue. Sed bibendum quam ut massa efficitur vestibulum. \"\n" +
            "  }\n" +
            "]"
}
