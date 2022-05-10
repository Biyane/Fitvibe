package com.example.fitvibe.utils

import android.os.Parcelable
import com.example.fitvibe.calendar.presentation.view.Day
import com.example.fitvibe.main.presentation.choose_time.presentation.view.CalendarData
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Trainer(
    val name: String,
    val profession: String,
    val status: Boolean,
    val duration: String,
    val image: String,
    var isFavourite: Boolean = false,
    val fitnessPicture: String,
    var description: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.w"
): Parcelable


val trainersList = mutableListOf(
    Trainer("Томирис Игликова", "Хатха-йога", true, "2 часа", "https://media.istockphoto.com/photos/portrait-of-a-beautiful-woman-at-the-gym-picture-id856797530?k=20&m=856797530&s=612x612&w=0&h=kFFhoXpDoF6jCmerJe-cZzOMKRvpl2orilNip2t3McU=", fitnessPicture = "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Trainer("Евгений Понасенков", "Кудалини-йога", true, "15 мин", "https://influencedigest.com/wp-content/uploads/2017/07/Top-20-Female-Fitness-Trainer-Bodybuilders.jpg", fitnessPicture = "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Trainer("Маржан Нурдаулет", "Тантра-йога", false, "30 мин", "https://s5.stc.all.kpcdn.net/woman/wp-content/uploads/2021/12/oblozhzhzhka-kopiya-2000-x-2000-px-kopiya-kopiya-86.jpg", fitnessPicture = "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Trainer("Глазинская Оксана", "Тренажерный зал", true, "1 час", "https://qpicture.ru/images/2016/10/27/7300000.jpg", fitnessPicture = "https://sportleader.kz/images/cms/opt%20tr/468884-pfz4p2-187.jpg"),
    Trainer("Глазинская Оксана", "Тренажерный зал", true, "1 час", "https://qpicture.ru/images/2016/10/27/7300000.jpg", fitnessPicture = "https://sportleader.kz/images/cms/opt%20tr/468884-pfz4p2-187.jpg"),
    Trainer("Жаканов Еркебулан", "Бокс", true, "1 час", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB22281ohVTIe73hktoSU534UxBcxDM3CRsQ&usqp=CAU", fitnessPicture = "https://img.prosports.kz/news/content//202203/162_4ddf4c799314ee6445a8c8281e2a46d3.jpg"),
    Trainer("Жаканов Еркебулан", "Бокс", true, "1 час", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB22281ohVTIe73hktoSU534UxBcxDM3CRsQ&usqp=CAU", fitnessPicture = "https://img.prosports.kz/news/content//202203/162_4ddf4c799314ee6445a8c8281e2a46d3.jpg"),
    Trainer("Карина Меликсетян", "Танцы", true, "2 час", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWgjstSgXlvMOI4irtwomKwAJww4p6ye1x4kwy9lYlB9lKZyvlZs2Re2CSP8vLx3ZtVkI&usqp=CAU", fitnessPicture = "https://s0.rbk.ru/v6_top_pics/media/img/6/48/755531726305486.jpg"),
    Trainer("Ксение Перепенко", "Плавание", true, "1 час", "https://thumbs.dreamstime.com/b/young-woman-personal-trainer-writes-training-plan-notebook-young-woman-personal-trainer-writes-training-plan-105071273.jpg", fitnessPicture = "https://worldclassmag.com/files/nodus_items/0005/2598/_cache/fit988x988-image-2598-1598254494.jpg"),
    Trainer("Ксение Перепенко", "Плавание", true, "1 час", "https://thumbs.dreamstime.com/b/young-woman-personal-trainer-writes-training-plan-notebook-young-woman-personal-trainer-writes-training-plan-105071273.jpg", fitnessPicture = "https://worldclassmag.com/files/nodus_items/0005/2598/_cache/fit988x988-image-2598-1598254494.jpg"),
    Trainer("Сергей Пашковский", "Тренажерный зал", true, "1 час", "https://media.istockphoto.com/photos/portrait-of-a-personal-trainer-in-the-gym-picture-id1040501222?k=20&m=1040501222&s=612x612&w=0&h=erVcLurW1lSQIC2LIhNJ1ppeV9-BllQGOxQeiA5l-js=", fitnessPicture = "https://sportleader.kz/images/cms/opt%20tr/468884-pfz4p2-187.jpg"),
    Trainer("Сергей Пашковский", "Тренажерный зал", true, "1 час", "https://media.istockphoto.com/photos/portrait-of-a-personal-trainer-in-the-gym-picture-id1040501222?k=20&m=1040501222&s=612x612&w=0&h=erVcLurW1lSQIC2LIhNJ1ppeV9-BllQGOxQeiA5l-js=", fitnessPicture = "https://sportleader.kz/images/cms/opt%20tr/468884-pfz4p2-187.jpg"),
    Trainer("Андрей Шпак", "Тэйквондо", true, "1 час", "https://thumbs.dreamstime.com/z/coach-men-s-fitness-hall-looks-shows-gesture-okay-97447754.jpg", fitnessPicture = "https://i.ytimg.com/vi/CsvCbR6F52I/sddefault.jpg"),
    Trainer("Игорь Герман", "Борьба", true, "2 часа", "https://p0.pikrepo.com/preview/263/30/.jpg", fitnessPicture = "https://media.istockphoto.com/photos/young-darkhair-active-woman-exercising-and-stratching-on-the-floor-at-picture-id1089783566?k=20&m=1089783566&s=612x612&w=0&h=WaJcTn3ruoiYUe4itEY1E0YE07hcZ1i56OZzSb18Ao4="),
    Trainer("Вячеслав Субботин", "EMS", true, "1.5 часа", "https://avatars.mds.yandex.net/get-altay/2433982/2a00000170a3e24130a7870b136368f5a463/XXL", fitnessPicture = "https://img.passeportsante.net/1200x675/2021-07-01/shutterstock-1180834207.webp"),
    Trainer("Меруерт Ермекова", "Стретчинг", true, "1 час", "https://thumbs.dreamstime.com/b/sporty-young-woman-fitness-coach-warm-up-stretch-hand-professional-yoga-trainer-warmup-training-keep-fit-morning-exercise-practice-174124964.jpg", fitnessPicture = "https://s0.rbk.ru/v6_top_pics/media/img/6/48/755531726305486.jpg"),
    Trainer("Ирина Родичева", "Йога", true, "1 час", "https://influencedigest.com/wp-content/uploads/2017/07/4.jpg", fitnessPicture = "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Trainer("Ирина Родичева", "Танцы", true, "1 час", "https://influencedigest.com/wp-content/uploads/2017/07/4.jpg", fitnessPicture = "https://s0.rbk.ru/v6_top_pics/media/img/6/48/755531726305486.jpg"),
    Trainer("Ирина Родичева", "Йога", true, "1 час", "https://influencedigest.com/wp-content/uploads/2017/07/4.jpg", fitnessPicture = "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Trainer("Элена Золотухина", "Горный Туризм", true, "2 часа", "https://img.freepik.com/free-photo/smiling-slim-strong-attractive-asian-female-fitness-coach-personal-instructor-trainer-pointing-herself-your-gym-logo_1258-25155.jpg", fitnessPicture = "https://img.championat.com/news/big/n/f/kak-pravilno-meditirovat-sovety-praktika_157684379279536979.jpg"),
    Trainer("Мухит Ахметов", "Сколазиние", true, "2 часа", "https://miro.medium.com/max/1400/0*QNwJnRw41KyL0ibt.jpeg", fitnessPicture = "https://img.championat.com/news/big/n/f/kak-pravilno-meditirovat-sovety-praktika_157684379279536979.jpg"),
)

data class Fitness(
    val name: String,
    val picture: String
)

val fitnessList = listOf<Fitness>(
    Fitness("Йога", "https://www.englishdom.com/dynamicus/blog-post/000/002/035/1588685131_content_700x455.jpg?1588685131572"),
    Fitness("Тренажерный зал", "https://sportleader.kz/images/cms/opt%20tr/468884-pfz4p2-187.jpg"),
    Fitness("Бокс", "https://img.prosports.kz/news/content//202203/162_4ddf4c799314ee6445a8c8281e2a46d3.jpg"),
    Fitness("Плавание", "https://worldclassmag.com/files/nodus_items/0005/2598/_cache/fit988x988-image-2598-1598254494.jpg"),
    Fitness("Танцы", "https://s0.rbk.ru/v6_top_pics/media/img/6/48/755531726305486.jpg"),
    Fitness("Медитация", "https://img.championat.com/news/big/n/f/kak-pravilno-meditirovat-sovety-praktika_157684379279536979.jpg"),

)

fun getDaysList(): List<Day> {
    val daysList = mutableListOf<Day>()
    for (i in 0..6) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, i)
        daysList.add(Day(day = getDayOfTheWeek(calendar.get(Calendar.DAY_OF_WEEK)), time = calendar.get(Calendar.DAY_OF_MONTH).toString()))
    }
    return daysList.toList()
}

fun getDayOfTheWeek(day: Int): String = when (day) {
    Calendar.SUNDAY -> "Вс"
    Calendar.MONDAY -> "Пн"
    Calendar.TUESDAY -> "Вт"
    Calendar.WEDNESDAY -> "Ср"
    Calendar.THURSDAY -> "Чт"
    Calendar.FRIDAY -> "Пт"
    else -> "Сб"
}

fun getFullDayOfTheWeek(day: Int): String = when (day) {
    Calendar.SUNDAY -> "Воскресенье"
    Calendar.MONDAY -> "Понеедльник"
    Calendar.TUESDAY -> "Вторник"
    Calendar.WEDNESDAY -> "Среда"
    Calendar.THURSDAY -> "Четверг"
    Calendar.FRIDAY -> "Пятница"
    else -> "Суббота"
}

val calendarHm: HashMap<String, MutableList<CalendarData>> = hashMapOf()

