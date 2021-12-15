package ru.smak.ui

import ru.smak.ui.painting.KeyFramesPanel
import ru.smak.ui.painting.SelectablePanel
import java.awt.Color
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import javax.swing.*

class AnimationFrame(private val selectablePanel: SelectablePanel) : JFrame() {
    val ctrlPanel : JPanel
    val kfLabel : JLabel
    val frameScroll : JScrollPane
    val keyFramesPanel : JPanel
    val addKeyFrame : JButton
    val createVideoBtn : JButton
    val frameTimeLbl : JLabel
    val frameTimeSpinner : JSpinner
    val frameTimeModel : SpinnerNumberModel
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Экскурсия"
        minimumSize = Dimension(1200, 700)
        kfLabel = JLabel().apply {
            text = "Ключевые кадры"
            font = getFont().deriveFont(16.0f)
        }
        frameTimeLbl = JLabel().apply {
            text = "Время смены ключевых кадров (с)"
        }
        frameTimeModel = SpinnerNumberModel(5, 1, 10, 1)
        frameTimeSpinner = JSpinner(frameTimeModel)
        createVideoBtn = JButton().apply {
            text = "Создать видео"
        }
        keyFramesPanel = KeyFramesPanel().apply {
            background = Color.GRAY
        }
        frameScroll = JScrollPane(keyFramesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER).apply {
            preferredSize = Dimension(300, 400)
        }



        addKeyFrame = JButton().apply {
            text = "Добавить ключевой кадр"
        }

        addKeyFrame.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                val img = BufferedImage(selectablePanel.width, selectablePanel.height, BufferedImage.TYPE_INT_RGB)
                val imgGr = img.createGraphics()
                selectablePanel.paint(imgGr)
                keyFramesPanel.addKeyFrame(img)
            }
        })

        ctrlPanel = JPanel().apply {
            background = Color.WHITE
            border = BorderFactory.createLineBorder(Color.BLACK)
        }
        layout = GroupLayout(contentPane).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(selectablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(ctrlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(createParallelGroup()
                        .addComponent(selectablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(ctrlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                    )
                    .addGap(4)
            )
        }
        ctrlPanel.layout = GroupLayout(ctrlPanel).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(50)
                    .addGroup(
                        createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(kfLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                            .addGap(30)
                            .addComponent(frameScroll, 300, 300, 300)
                            .addGap(10)
                            .addComponent(addKeyFrame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(15)
                            .addComponent(frameTimeLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(frameTimeSpinner, 100, GroupLayout.PREFERRED_SIZE, Int.MAX_VALUE)
                    )
                    .addGap(50)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(15)
                    .addComponent(kfLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(30)
                    .addComponent(frameScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE , 400)
                    .addGap(10)
                    .addComponent(addKeyFrame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(15)
                    .addComponent(frameTimeLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(frameTimeSpinner, 20, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30 , Int.MAX_VALUE)
            )
        }


    }


}
